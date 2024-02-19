package com.jason.tics.test.b.listener;

import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.common.rocketmq.entity.Pair;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
@RocketMQMessageListener(topic = RocketMqConstant.USER_LIKE_TOPIC,
        consumerGroup = RocketMqConstant.USER_LIKE_TOPIC)
@Slf4j
public class UserLikeConsumer implements RocketMQListener<Pair<Long, String>> {
    @Override
    public void onMessage(Pair<Long, String> message) {
        log.info("user["+message.getKey()+"] like the content["+message.getValue()+"]");
    }
}
