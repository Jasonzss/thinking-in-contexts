package com.jason.tics.search.component.listener;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import com.jason.tics.api.content.bo.VideoPostBo;
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
@RocketMQMessageListener(topic = RocketMqConstant.CONTENT_VIDEO_UPSERT_TOPIC,
        consumerGroup = RocketMqConstant.CONTENT_VIDEO_UPSERT_TOPIC)
@Slf4j
public class VideoContentUpsertConsumer implements RocketMQListener<VideoPostBo> {
    @Autowired
    private ElasticsearchClient esClient;

    @Override
    public void onMessage(VideoPostBo message) {
        try {
            UpdateResponse<VideoPostBo> response = esClient.update(builder -> builder
                    .index(EsConstant.VIDEO_INDEX)
                    .id(message.getVideoId())
                    .doc(message)
                    //如果要修改的文档不存在则新插入
                    .docAsUpsert(true), VideoPostBo.class);

            log.info("uploaded video info saved in elasticsearch successfully, video id {}", response.result());
        } catch (IOException e) {
            log.error("elasticsearch upsert failed", e);
        }
    }
}
