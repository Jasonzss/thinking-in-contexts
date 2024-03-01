package com.jason.tics.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.jason.tics.api.search.bo.SearchResult;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.search.constant.EsConstant;
import com.jason.tics.search.service.ContentSearchService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Jason
 */
@Service
public class ContentSearchServiceImpl implements ContentSearchService {
    @Autowired
    private ElasticsearchClient esClient;

    /**
     * 综合查询
     *
     * page 从1开始
     */
    @Override
    public SearchResult<?> searchAll(String search, int page, int size, String... types){
        ArrayList<String> contentTypes = Lists.newArrayList(types);
        SearchResponse<?> r;
        try {
            r = esClient.search(builder -> builder.index(contentTypes)
                            .query(queryBuilder -> queryBuilder
                                    .bool(boolQueryBuilder -> {
                                        for (String type : contentTypes) {
                                            //三种类型分字段查询，最终整合到一起
                                            if (EsConstant.AUDIO_INDEX.equals(type)) {
                                                boolQueryBuilder.should(QueryBuilders
                                                        .multiMatch(multiMatchQueryBuilder -> multiMatchQueryBuilder
                                                                .fields(EsConstant.TITLE, EsConstant.INTRODUCTION,
                                                                        EsConstant.AUDIO_TEXT)
                                                                .query(search)));
                                            } else if (EsConstant.ESSAY_INDEX.equals(type)) {
                                                boolQueryBuilder.should(QueryBuilders
                                                        .multiMatch(multiMatchQueryBuilder -> multiMatchQueryBuilder
                                                                .fields(EsConstant.TITLE, EsConstant.ESSAY)
                                                                .query(search)));
                                            } else if (EsConstant.VIDEO_INDEX.equals(type)) {
                                                boolQueryBuilder.should(QueryBuilders
                                                        .multiMatch(multiMatchQueryBuilder -> multiMatchQueryBuilder
                                                                .fields(EsConstant.TITLE, EsConstant.INTRODUCTION,
                                                                        EsConstant.VIDEO_SUBTITLE)
                                                                .query(search)));
                                            }
                                        }
                                        return boolQueryBuilder;
                                    }))
                            .from((page - 1) * size)
                            .size(size),
                    Map.class);
        } catch (IOException e) {
            throw new TicsException(ExceptionResponseEnum.ELASTICSEARCH_BROKEN_DOWN);
        }

        return new SearchResult<>(page, r.hits().total().value(), r.hits().hits());
    }
}
