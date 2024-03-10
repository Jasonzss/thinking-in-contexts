package com.jason.tics.order.component.callback;

import com.jason.tics.common.rocketmq.SendCallbackFactory;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.order.domain.pojo.vo.OrderVo;
import com.jason.tics.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Slf4j
@Component
public class OrderTimeoutSendCallback implements SendCallbackFactory {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RocketMQTemplate orderTimeOutMqTemplate;

    @Override
    public SendCallback getSendCallback(Object payload) {
        return new SendCallback() {
            private final long orderId = ((OrderVo) payload).getOrderId();
            private static final int MAX_RETRY = 3;
            private int retryCount = 0; // 当前重试次数

            @Override
            public void onSuccess(SendResult sendResult) {
                //订单超时，关闭订单
                orderService.timeout(orderId);
                log.info("The order [{}] timeout was cancelled", orderId);
            }

            @Override
            public void onException(Throwable e) {
                // 消息发送失败，尝试重试发送
                if (retryCount < MAX_RETRY) {
                    retryCount++;
                    log.error("Failed to send message, retrying (" + retryCount + "/" + MAX_RETRY + ")...");
                    orderTimeOutMqTemplate.asyncSend(RocketMqConstant.ORDER_TIME_OUT_TOPIC,
                            MessageBuilder.withPayload(payload).build(),
                            this, RocketMqConstant.TIMEOUT, RocketMqConstant.ORDER_CANCEL_TIME_LEVEL);
                } else {
                    // 已达到最大重试次数，不再重试，直接关闭订单
                    orderService.timeout(orderId);
                    log.error("Failed to send message after " + MAX_RETRY + " retries: " + e.getMessage());
                    //TODO 通知管理员

                }
            }
        };
    }
}
