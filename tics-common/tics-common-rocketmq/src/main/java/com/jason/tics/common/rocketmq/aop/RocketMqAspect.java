package com.jason.tics.common.rocketmq.aop;

import cn.hutool.core.bean.BeanUtil;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.common.rocketmq.annotation.SendMessage;
import com.jason.tics.common.rocketmq.config.RocketMqAdapter;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason
 */
@Aspect
@Component
@Slf4j
public class RocketMqAspect implements ApplicationContextAware, InitializingBean {
    /**
     * 所有rocketMqTemplate的集合
     * key是rocketMqTemplate对应的topic
     * value是rocketMqTemplate
     */
    private Map<String, RocketMQTemplate> rocketMQTemplateMap;

    private ApplicationContext applicationContext;

    @Autowired
    private RocketMqAdapter rocketMqAdapter;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        Map<String, RocketMQTemplate> beansOfType = applicationContext.getBeansOfType(RocketMQTemplate.class);
        rocketMQTemplateMap = new HashMap<>();
        for (RocketMQTemplate t : beansOfType.values()) {
            rocketMQTemplateMap.put(t.getProducer().getProducerGroup(), t);
        }
    }

    @Pointcut("@annotation(com.jason.tics.common.rocketmq.annotation.SendMessage)")
    public void mqPointcut() { }

    @Around("mqPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        SendMessage annotation = signature.getMethod().getAnnotation(SendMessage.class);

        Object res = point.proceed();
        Object payload = null;

        if (annotation.source() == PayloadSource.RESULT){
            //选择返回值为消息
            payload = res;
            if (annotation.targetPojo() != Void.class) {
                //转换成目标pojo
                payload = annotation.targetPojo().newInstance();
                BeanUtil.copyProperties(res, payload);
            }
        }else if (annotation.source() == PayloadSource.PARAM) {
            //选择入参为消息
            Object[] args = point.getArgs();
            int length = annotation.paramsIndex().length;
            if (length > 1) {
                //多入参
                Parameter[] params = signature.getMethod().getParameters();
                Map<Object, Object> map = new HashMap<>();
                for (int i : annotation.paramsIndex()) {
                    map.put(params[i].getName(), args[i]);
                }
                payload = map;
            } else if (length == 1){
                //单入参
                payload = args[0];
            } else {
                throw new TicsException(ExceptionResponseEnum.INVALID_PAYLOAD);
            }
        }

        RocketMQTemplate template = rocketMQTemplateMap.get(annotation.topic());
        if (template == null){
            log.warn("can't find the rocketmqTemplate of topic {}, it is recommended to configure it explicitly", annotation.topic());
            template = rocketMqAdapter.getTemplateByTopicName(annotation.topic());
        }
        //发送消息
        if (annotation.synchronous()){
            //同步发送
            template.syncSend(annotation.topic(), MessageBuilder.withPayload(payload).build(), RocketMqConstant.TIMEOUT);
        }else {
            //异步发送
            template.asyncSend(annotation.topic(), MessageBuilder.withPayload(payload).build(), new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("RocketMqAspect send {} message success", annotation.topic());
                }

                @Override
                public void onException(Throwable e) {
                    throw new TicsException("RocketMqAspect send "+annotation.topic()+" message failed", e);
                }
            }, RocketMqConstant.TIMEOUT);
        }

        return res;
    }
}
