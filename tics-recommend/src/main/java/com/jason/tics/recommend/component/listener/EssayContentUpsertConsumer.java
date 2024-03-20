package com.jason.tics.recommend.component.listener;

import com.jason.tics.api.content.bo.ContentType;
import com.jason.tics.api.content.bo.EssayPostBo;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.recommend.domain.Preferences;
import com.jason.tics.recommend.domain.recommender.content.EssayRecommender;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
@RocketMQMessageListener(topic = RocketMqConstant.CONTENT_ESSAY_UPSERT_TOPIC,
        consumerGroup = RocketMqConstant.RECOMMEND_GROUP)
@Slf4j
public class EssayContentUpsertConsumer implements RocketMQListener<EssayPostBo> {
    @Autowired
    private EssayRecommender essayRecommender;

    @Override
    public void onMessage(EssayPostBo message) {
        long itemId = Long.parseLong(message.getEssayId().replace(ContentType.ESSAY.getIdPrefix(), ""));
        essayRecommender.setPreference(message.getAuthorId(), itemId, Preferences.AUTHOR);
    }
}
