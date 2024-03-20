package com.jason.tics.recommend.component.listener;

import com.jason.tics.api.content.bo.ContentType;
import com.jason.tics.api.content.bo.VideoPostBo;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.recommend.domain.Preferences;
import com.jason.tics.recommend.domain.recommender.content.VideoRecommender;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
@RocketMQMessageListener(topic = RocketMqConstant.CONTENT_VIDEO_UPSERT_TOPIC,
        consumerGroup = RocketMqConstant.RECOMMEND_GROUP)
@Slf4j
public class VideoContentUpsertConsumer implements RocketMQListener<VideoPostBo> {
    @Autowired
    private VideoRecommender videoRecommender;

    @Override
    public void onMessage(VideoPostBo message) {
        long itemId = Long.parseLong(message.getVideoId().replace(ContentType.VIDEO.getIdPrefix(), ""));
        videoRecommender.setPreference(message.getAuthorId(), itemId, Preferences.AUTHOR);
    }
}
