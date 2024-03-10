package com.jason.tics.common.rocketmq;

import org.apache.rocketmq.client.producer.SendCallback;

/**
 * @author Jason
 */
public interface SendCallbackFactory {
    SendCallback getSendCallback(Object payload);
}
