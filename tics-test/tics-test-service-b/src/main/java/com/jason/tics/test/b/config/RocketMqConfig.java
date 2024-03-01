package com.jason.tics.test.b.config;

import com.jason.tics.common.rocketmq.config.RocketMqAdapter;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author Jason
 */
@Configuration
public class RocketMqConfig {
    @Autowired
    private RocketMqAdapter rocketMqAdapter;

    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate testMqTemplate(){
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.TEST_TOPIC);
    }
}
