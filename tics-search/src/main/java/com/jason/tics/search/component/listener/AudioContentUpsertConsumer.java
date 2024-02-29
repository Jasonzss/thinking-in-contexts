package com.jason.tics.search.component.listener;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import com.jason.tics.api.content.bo.AudioPostBo;
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
@RocketMQMessageListener(topic = RocketMqConstant.CONTENT_AUDIO_UPSERT_TOPIC,
        consumerGroup = RocketMqConstant.CONTENT_AUDIO_UPSERT_TOPIC)
@Slf4j
public class AudioContentUpsertConsumer implements RocketMQListener<AudioPostBo>{
    @Autowired
    private ElasticsearchClient esClient;

    @Override
    public void onMessage(AudioPostBo message) {
        try {
            UpdateResponse<AudioPostBo> response = esClient.update(builder -> builder
                    .index(EsConstant.AUDIO_INDEX)
                    .id(message.getAudioId())
                    .doc(message)
                    //如果要修改的文档不存在则新插入
                    .docAsUpsert(true), AudioPostBo.class);

            log.info("uploaded audio info saved in elasticsearch successfully, audio id {}", response.result());
        } catch (IOException e) {
            log.error("elasticsearch upsert failed", e);
        }
    }
}
