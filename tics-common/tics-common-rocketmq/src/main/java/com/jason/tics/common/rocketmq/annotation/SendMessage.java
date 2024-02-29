package com.jason.tics.common.rocketmq.annotation;

import com.jason.tics.common.rocketmq.aop.PayloadSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 以方法的返回值作为rocketmq的payload发送消息
 * @author Jason
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SendMessage {
    /**
     * 发送消息的topic
     */
    String topic() default "";

    /**
     * 默认为异步发送
     * @return 是否同步发送消息
     */
    boolean synchronous() default false;

    /**
     * 发送消息的消息来源
     * 默认消息源为方法返回值
     */
    PayloadSource source() default PayloadSource.RESULT;

    /**
     * 仅在source()确定为 PayloadSource.PARAM 时生效
     * 说明作为消息发送的入参的index是哪些
     * 默认只发送第一位参数
     * 当发送多个参数时将会把入参的名称以及值存入Map中发送
     * @return 作为消息发送的入参的index集合
     */
    int[] paramsIndex() default {0};

    /**
     * 将源对象转换为目标对象后再作为payload发送消息
     * 转换逻辑基于 {@link cn.hutool.core.bean.BeanUtil}
     * @return 转换后的payload的类型
     */
    Class<?> targetPojo() default Void.class;
}
