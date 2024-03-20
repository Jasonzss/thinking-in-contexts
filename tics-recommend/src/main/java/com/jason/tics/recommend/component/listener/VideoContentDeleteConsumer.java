package com.jason.tics.recommend.component.listener;

import com.jason.tics.api.content.bo.ContentType;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.recommend.domain.repository.VideoTastePreferencesRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
@RocketMQMessageListener(topic = RocketMqConstant.CONTENT_VIDEO_DELETE_TOPIC,
        consumerGroup = RocketMqConstant.RECOMMEND_GROUP)
@Slf4j
public class VideoContentDeleteConsumer implements RocketMQListener<String> {
    @Autowired
    private VideoTastePreferencesRepository videoTastePreferencesRepository;

    @Override
    public void onMessage(String message) {
        long itemId = Long.parseLong(message.replace(ContentType.VIDEO.getIdPrefix(), ""));
        videoTastePreferencesRepository.deleteAllByItemId(itemId);
    }
}
