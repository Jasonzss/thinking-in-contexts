package com.jason.tics.search.component.listener;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.jason.tics.api.content.bo.VideoPostBo;
import com.jason.tics.search.SearchApplication;
import com.jason.tics.search.constant.EsConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author Jason
 */
@SpringBootTest(classes = SearchApplication.class)
@Slf4j
public class VideoContentUpsertConsumerTest {
    @Autowired
    private RocketMQTemplate videoUpsertMqTemplate;
    @Autowired
    private ElasticsearchClient client;

    @Test
    public void test() throws IOException {
        SearchResponse<VideoPostBo> search = client
                .search(builder -> builder.index(EsConstant.VIDEO_INDEX), VideoPostBo.class);
        log.info(search.toString());
    }
}
