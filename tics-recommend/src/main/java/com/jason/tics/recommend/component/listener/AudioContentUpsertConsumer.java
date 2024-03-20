package com.jason.tics.recommend.component.listener;

import com.jason.tics.api.content.bo.AudioPostBo;
import com.jason.tics.api.content.bo.ContentType;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.recommend.domain.Preferences;
import com.jason.tics.recommend.domain.recommender.content.AudioRecommender;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
@RocketMQMessageListener(topic = RocketMqConstant.CONTENT_AUDIO_UPSERT_TOPIC,
        consumerGroup = RocketMqConstant.RECOMMEND_GROUP)
@Slf4j
public class AudioContentUpsertConsumer implements RocketMQListener<AudioPostBo>{
    @Autowired
    private AudioRecommender audioRecommender;

    @Override
    public void onMessage(AudioPostBo message) {
        // TODO 内容刚创建，缺乏其他用户的偏好数据
        long itemId = Long.parseLong(message.getAudioId().replace(ContentType.AUDIO.getIdPrefix(), ""));
        audioRecommender.setPreference(message.getAuthorId(), itemId, Preferences.AUTHOR);
    }
}
