package com.jason.tics.common.rocketmq.component.callback;

import com.jason.tics.common.rocketmq.SendCallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Slf4j
@Component
public class DefaultSendCallback implements SendCallbackFactory {
    @Override
    public SendCallback getSendCallback(Object payload) {
        return new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Message sent successfully: " + sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable e) {
                log.error("Failed to send message: " + e.getMessage());
            }
        };
    }
}
