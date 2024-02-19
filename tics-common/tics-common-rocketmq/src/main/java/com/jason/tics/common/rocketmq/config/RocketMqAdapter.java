package com.jason.tics.common.rocketmq.config;

import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

/**
 * @author Jason
 */
@Configuration
public class RocketMqAdapter {
//    @Autowired
//    private RocketMQMessageConverter rocketMqMessageConverter;

    @Value("${rocketmq.name-server}")
    private String nameServer;

    public RocketMQTemplate getTemplateByTopicName(String topic){
        //构建producer
        DefaultMQProducer producer = new DefaultMQProducer(topic);
        producer.setNamesrvAddr(nameServer);
        producer.setRetryTimesWhenSendFailed(2);
        producer.setSendMsgTimeout((int) RocketMqConstant.TIMEOUT);

        //使用producer构建MQTemplate
        RocketMQTemplate mqTemplate = new RocketMQTemplate();
        mqTemplate.setProducer(producer);
        mqTemplate.setMessageConverter(new MappingJackson2MessageConverter());
        return mqTemplate;
    }
}
