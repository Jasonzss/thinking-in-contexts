package com.jason.tics.common.rocketmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.messaging.MappingFastJsonMessageConverter;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.common.rocketmq.entity.Pair;
import com.jason.tics.test.a.TestApplicationA;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Arrays;

/**
 * 使用此测试前需要将RocketMqNameServer、RocketMqBroker、TestApplicationB开启
 * @author Jason
 */
@SpringBootTest(classes = TestApplicationA.class)
@Slf4j
public class RocketMqTest {
    @Autowired
    private RocketMQTemplate userLikeMqTemplate;

    @Test
    public void testSyncSend(){
        SendResult result = userLikeMqTemplate.syncSend(RocketMqConstant.USER_LIKE_TOPIC,
                MessageBuilder.withPayload(new Pair<>(1L, "1")).build(), RocketMqConstant.TIMEOUT);
        log.info("同步消息-mq发送结果："+result.getSendStatus());
    }

//    private FastJsonConfig fastJsonConfig = new FastJsonConfig();
    @Autowired
    private RocketMQMessageConverter converter;

    @Test
    public void testSyncSend1() {
        FastJsonConfig fastJsonConfig;
        for (MessageConverter messageConverter : ((CompositeMessageConverter) converter.getMessageConverter()).getConverters()) {
            if (messageConverter instanceof MappingFastJsonMessageConverter){
                MappingFastJsonMessageConverter c = (MappingFastJsonMessageConverter)messageConverter;
                fastJsonConfig = c.getFastJsonConfig();

                Pair<Long, String> pair = new Pair<>(1L, "1");
                byte[] s = JSON.toJSONBytes(fastJsonConfig.getCharset(), pair, fastJsonConfig.getSerializeConfig(), fastJsonConfig.getSerializeFilters(),
                        fastJsonConfig.getDateFormat(), JSON.DEFAULT_GENERATE_FEATURE, fastJsonConfig.getSerializerFeatures());
                log.info(Arrays.toString(s));
                Pair<Long, String> pair1 = JSON.parseObject(s, Pair.class);
                System.out.println(pair1);
            }
        }
    }

}
