package com.jason.tics.search.config;

import com.jason.tics.common.rocketmq.config.RocketMqAdapter;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author Jason
 */
@RefreshScope
@Configuration
public class RocketMqConfig {
    @Autowired
    private RocketMqAdapter rocketMqAdapter;

    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate videoUpsertMqTemplate(){
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.CONTENT_VIDEO_UPSERT_TOPIC);
    }

    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate audioUpsertMqTemplate(){
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.CONTENT_AUDIO_UPSERT_TOPIC);
    }

    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate essayUpsertMqTemplate(){
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.CONTENT_ESSAY_UPSERT_TOPIC);
    }
}
