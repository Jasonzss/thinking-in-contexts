package com.jason.tics.search.component;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import com.jason.tics.search.constant.EsConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Jason
 */
@Component
@Slf4j
@Lazy(false)
public class EsIndexInitializingBean implements InitializingBean {
    @Autowired
    private ElasticsearchClient esClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        initAudioIndex();
        initEssayIndex();
        initVideoIndex();
    }

    private void initVideoIndex() throws IOException {
        ElasticsearchIndicesClient indices = esClient.indices();
        if (!indices.exists(builder -> builder.index(EsConstant.VIDEO_INDEX)).value()) {
            //没发现index，新建一个
            CreateIndexResponse response = indices.create(builder -> builder
                    .index(EsConstant.VIDEO_INDEX)
                    .mappings(typeMappingBuilder -> typeMappingBuilder
                            .properties(EsConstant.VIDEO_URL, propertyBuilder -> propertyBuilder.keyword(k -> k))
                            .properties(EsConstant.INTRODUCTION, pb -> pb
                                    .text(textBuilder -> textBuilder
                                            .analyzer(EsConstant.IK_MAX_WORD_ANALYZER)
                                            .searchAnalyzer(EsConstant.IK_SMART_ANALYZER)))
                            .properties(EsConstant.COVER_IMAGE_URL, pb -> pb.keyword(k -> k))
                            .properties(EsConstant.CREATE_TIME, pb -> pb.date(dateBuilder -> dateBuilder))
                            .properties(EsConstant.UPDATE_TIME, pb -> pb.date(dateBuilder -> dateBuilder))
                            .properties(EsConstant.VIDEO_ID, pb -> pb.keyword(k -> k))
                            .properties(EsConstant.AUTHOR_ID, pb -> pb.long_(k -> k))
                            .properties(EsConstant.TITLE, pb -> pb
                                    .text(textBuilder -> textBuilder
                                            .analyzer(EsConstant.IK_MAX_WORD_ANALYZER)
                                            .searchAnalyzer(EsConstant.IK_SMART_ANALYZER)))
                            .properties(EsConstant.QUANTITY_OF_VIEWS, pb -> pb
                                    .integer(integerBuilder -> integerBuilder)))
            );

            log.info("index [{}] create success", EsConstant.AUDIO_INDEX);
        }else{
            log.info("index [{}] already exists, creation canceled", EsConstant.AUDIO_INDEX);
        }

        log.info("{}", indices.get(indexBuilder -> indexBuilder.index(EsConstant.VIDEO_INDEX)).result());
    }

    private void initAudioIndex() throws IOException {
        ElasticsearchIndicesClient indices = esClient.indices();
        if (!indices.exists(builder -> builder.index(EsConstant.AUDIO_INDEX)).value()) {
            //没发现index，新建一个
            CreateIndexResponse response = indices.create(builder -> builder
                    .index(EsConstant.AUDIO_INDEX)
                    .mappings(typeMappingBuilder -> typeMappingBuilder
                            .properties(EsConstant.AUDIO_URL, propertyBuilder -> propertyBuilder.keyword(k -> k))
                            .properties(EsConstant.INTRODUCTION, pb -> pb
                                    .text(textBuilder -> textBuilder
                                            .analyzer(EsConstant.IK_MAX_WORD_ANALYZER)
                                            .searchAnalyzer(EsConstant.IK_SMART_ANALYZER)))
                            .properties(EsConstant.COVER_IMAGE_URL, pb -> pb.keyword(k -> k))
                            .properties(EsConstant.CREATE_TIME, pb -> pb.date(dateBuilder -> dateBuilder))
                            .properties(EsConstant.UPDATE_TIME, pb -> pb.date(dateBuilder -> dateBuilder))
                            .properties(EsConstant.AUDIO_ID, pb -> pb.keyword(k -> k))
                            .properties(EsConstant.AUTHOR_ID, pb -> pb.long_(k -> k))
                            .properties(EsConstant.TITLE, pb -> pb
                                    .text(textBuilder -> textBuilder
                                            .analyzer(EsConstant.IK_MAX_WORD_ANALYZER)
                                            .searchAnalyzer(EsConstant.IK_SMART_ANALYZER)))
                            .properties(EsConstant.QUANTITY_OF_VIEWS, pb -> pb
                                    .integer(integerBuilder -> integerBuilder)))
            );

            log.info("index [{}] create success", EsConstant.AUDIO_INDEX);
        }else{
            log.info("index [{}] already exists, creation canceled", EsConstant.AUDIO_INDEX);
        }

        log.info("{}", indices.get(indexBuilder -> indexBuilder.index(EsConstant.AUDIO_INDEX)).result());
    }

    private void initEssayIndex() throws IOException {
        ElasticsearchIndicesClient indices = esClient.indices();
        if (!indices.exists(builder -> builder.index(EsConstant.ESSAY_INDEX)).value()) {
            //没发现index，新建一个
            CreateIndexResponse response = indices.create(builder -> builder
                    .index(EsConstant.ESSAY_INDEX)
                    .mappings(typeMappingBuilder -> typeMappingBuilder
                            .properties(EsConstant.ESSAY_URL, propertyBuilder -> propertyBuilder.keyword(k -> k))
                            .properties(EsConstant.COVER_IMAGE_URL, pb -> pb.keyword(k -> k))
                            .properties(EsConstant.CREATE_TIME, pb -> pb.date(dateBuilder -> dateBuilder))
                            .properties(EsConstant.UPDATE_TIME, pb -> pb.date(dateBuilder -> dateBuilder))
                            .properties(EsConstant.ESSAY_ID, pb -> pb.keyword(k -> k))
                            .properties(EsConstant.AUTHOR_ID, pb -> pb.long_(k -> k))
                            .properties(EsConstant.TITLE, pb -> pb
                                    .text(textBuilder -> textBuilder
                                            .analyzer(EsConstant.IK_MAX_WORD_ANALYZER)
                                            .searchAnalyzer(EsConstant.IK_SMART_ANALYZER)))
                            .properties(EsConstant.QUANTITY_OF_VIEWS, pb -> pb
                                    .integer(integerBuilder -> integerBuilder)))
            );

            log.info("index [{}] create success", EsConstant.ESSAY_INDEX);
        }else{
            log.info("index [{}] already exists, creation canceled", EsConstant.ESSAY_INDEX);
        }

        log.info("{}", indices.get(indexBuilder -> indexBuilder.index(EsConstant.ESSAY_INDEX)).result());
    }
}
