package com.jason.tics.search.component.listener;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.search.constant.EsConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Jason
 */
@Component
@RocketMQMessageListener(topic = RocketMqConstant.CONTENT_VIDEO_DELETE_TOPIC,
        consumerGroup = RocketMqConstant.CONTENT_VIDEO_DELETE_TOPIC)
@Slf4j
public class VideoContentDeleteConsumer implements RocketMQListener<String> {
    @Autowired
    private ElasticsearchClient esClient;

    @Override
    public void onMessage(String message) {
        try {
            DeleteResponse delete = esClient.delete(builder -> builder.index(EsConstant.VIDEO_INDEX).id(message));
            if (delete.result().equals(Result.NotFound)) {
                log.warn("deleted video [{}] doesn't exist", message);
            }else {
                log.info("video [{}] had been deleted", message);
            }
        } catch (IOException e) {
            log.error("elasticsearch delete failed", e);
        }
    }
}
