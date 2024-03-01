package com.jason.tics.common.rocketmq;

import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.common.rocketmq.entity.Pair;
import com.jason.tics.test.a.TestApplicationA;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 使用此测试前需要将RocketMqNameServer、RocketMqBroker、TestApplicationB开启
 * @author Jason
 */
@SpringBootTest(classes = TestApplicationA.class)
@Slf4j
public class RocketMqTest {
    @Autowired
    private RocketMQTemplate testMqTemplate;

    @Test
    public void testSyncSend(){
        SendResult result = testMqTemplate.syncSend(RocketMqConstant.TEST_TOPIC,
                MessageBuilder.withPayload(new Pair<>(1L, "1")).build(), RocketMqConstant.TIMEOUT);
        log.info("同步消息-mq发送结果："+result.getSendStatus());
    }

    @Test
    public void test() throws InterruptedException {
        testMqTemplate.asyncSend(RocketMqConstant.TEST_TOPIC,
                MessageBuilder.withPayload(new Pair<>(1L, "1")).build(),
                new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("成功！！！！！！！！！！！！！！{}", sendResult);
                    }

                    @Override
                    public void onException(Throwable e) {
                        log.info("失败！！！！！！！！！！！！！！", e);
                    }
                });
        log.info("wait-----------------------------------");

        Thread.sleep(2000);
        log.info("awake-----------------------------------");
    }
}
