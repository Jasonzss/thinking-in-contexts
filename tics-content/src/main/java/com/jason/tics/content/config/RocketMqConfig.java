package com.jason.tics.content.config;

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
@Configuration
@RefreshScope
public class RocketMqConfig {
    @Autowired
    private RocketMqAdapter rocketMqAdapter;

    /**
     * 用户收入喜欢收藏夹事件
     */
    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate userFavorMqTemplate(){
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.USER_FAVOR_TOPIC);
    }

    /**
     * 用户收藏事件
     */
    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate userCollectMqTemplate(){
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.USER_COLLECT_TOPIC);
    }

    /**
     * 用户点赞事件
     */
    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate userLikeMqTemplate(){
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.USER_LIKE_TOPIC);
    }

    /**
     * 用户浏览content事件
     */
    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate userViewedContentMqTemplate(){
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.USER_CONTENT_VIEWED_TOPIC);
    }

    /**
     * video上传
     */
    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate videoUpsertMqTemplate(){
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.CONTENT_VIDEO_UPSERT_TOPIC);
    }
    /**
     * audio上传
     */

    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate audioUpsertMqTemplate(){
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.CONTENT_AUDIO_UPSERT_TOPIC);
    }

    /**
     * essay上传
     */
    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate essayUpsertMqTemplate(){
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.CONTENT_ESSAY_UPSERT_TOPIC);
    }
}
