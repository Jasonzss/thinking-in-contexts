package com.jason.tics.search.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.SuggestMode;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.elasticsearch.indices.PutMappingResponse;
import co.elastic.clients.elasticsearch.sql.QueryResponse;
import co.elastic.clients.json.JsonData;
import com.jason.tics.search.SearchApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jason
 */
@Slf4j
@SpringBootTest(classes = SearchApplication.class)
public class EsClientTest {

    private static final String INDEX_NAME = "poet-index";
    
    @Autowired
    private ElasticsearchClient client;

    /**
     * 创建索引
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndexResponse response = client.indices().create(builder -> builder
                //index的配置
                .settings(indexSettingsBuilder -> indexSettingsBuilder.numberOfReplicas("1").numberOfShards("2"))
                //设置index的字段
                .mappings(typeMappingBuilder -> typeMappingBuilder
                        .properties("age", propertyBuilder -> propertyBuilder.integer(integerNumberPropertyBuilder -> integerNumberPropertyBuilder))
                        .properties("name", propertyBuilder -> propertyBuilder.keyword(keywordPropertyBuilder -> keywordPropertyBuilder))
                        .properties("poems", propertyBuilder -> propertyBuilder.text(textPropertyBuilder -> textPropertyBuilder.analyzer("ik_max_word").searchAnalyzer("ik_max_word")))
                        .properties("about", propertyBuilder -> propertyBuilder.text(textPropertyBuilder -> textPropertyBuilder.analyzer("ik_max_word").searchAnalyzer("ik_max_word")))
                        .properties("success", propertyBuilder -> propertyBuilder.text(textPropertyBuilder -> textPropertyBuilder.analyzer("ik_max_word").searchAnalyzer("ik_max_word")))
                )
                //设置index的名字
                .index(INDEX_NAME));
        log.info("acknowledged={}", response.acknowledged());
        log.info("index={}", response.index());
    }

    /**
     * 修改索引的_mapping信息
     * 字段可以新增，已有的字段只能修改字段的search_analyzer属性
     */
    @Test
    public void modifyIndex() throws IOException {
        PutMappingResponse response = client.indices().putMapping(typeMappingBuilder -> typeMappingBuilder
                .index(INDEX_NAME)  //要修改的index的名称:poet-index（诗人索引）
                .properties("age", propertyBuilder -> propertyBuilder.integer(integerNumberPropertyBuilder -> integerNumberPropertyBuilder))
                .properties("name", propertyBuilder -> propertyBuilder.keyword(keywordPropertyBuilder -> keywordPropertyBuilder))
                .properties("poems", propertyBuilder -> propertyBuilder.text(
                        //修改poems字段的searchAnalyzer为IkSmart
                        textPropertyBuilder -> textPropertyBuilder.analyzer("ik_max_word").searchAnalyzer("ik_smart"))
                )
        );
        log.info("acknowledged={}", response.acknowledged());
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexResponse response = client.indices().delete(builder -> builder.index(INDEX_NAME));
        log.info("acknowledged={}", response.acknowledged());
    }

    /**
     * 查询索引列表
     */
    @Test
    public void getIndex() throws IOException {
        //使用 * 也可以
//        GetIndexResponse response = client.indices().get(builder -> builder.index("_all"));
        GetIndexResponse response = client.indices().get(builder -> builder.index("*"));
        log.info(response.result().keySet().toString());
        log.info(response.result().toString());
    }

    /**
     * 查询索引详情
     */
    @Test
    public void getIndexDetail() throws IOException {
        GetIndexResponse response = client.indices().get(builder -> builder.index(INDEX_NAME));
        log.info(response.result().get(INDEX_NAME).toString());
        log.info(response.result().toString());
    }

    /**
     * 创建文档
     */
    @Test
    public void createDoc() throws IOException {
        Map<String, Object> doc = new HashMap<>();
        doc.put("age", 30);
        doc.put("name", "李白");
        doc.put("poems", "静夜思");
        doc.put("about", "字太白，唐朝人");
        doc.put("success", "创造了古代浪漫主义文学高峰、歌行体和七绝达到后人难及的高度");

        //以map为字段载体创建id为1的文档
        CreateResponse response = client.create(builder -> builder.index(INDEX_NAME).id("1").document(doc));
        log.info(response.toString());

        //以index映射的Poet对象为字段载体创建id为2的文档
        Poet poet = new Poet(
                31, "杜甫", "登高", "字子美", "唐朝伟大的现实主义文学作家，唐诗思想艺术的集大成者");
        response = client.create(builder -> builder.index(INDEX_NAME).id("2").document(poet));
        log.info(response.toString());
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDoc() throws IOException {
        DeleteResponse response = client.delete(builder -> builder
                //删除id为2的诗人，id为1的没法一起删除，如果想删除多个，参考下面的批量删除
                .index(INDEX_NAME).id("1").id("2"));
        log.info(response.toString());
    }

    /**
     * 修改文档，只修改设置的字段（部分修改）
     */
    @Test
    public void updateDoc() throws IOException {
        Map<String, Object> doc = new HashMap<>();
        //修改名字和年龄
        doc.put("age", 33);
        doc.put("name", "李白2");

        //以map为数据载体修改
        UpdateResponse<Map> response = client.update(builder -> builder.index(INDEX_NAME).id("1").doc(doc), Map.class);
        log.info(response.toString());

        //以映射对象为载体修改
        Poet poet = new Poet();
        poet.setAge(40);
        poet.setName("杜甫2");
        UpdateResponse<Poet> response1 = client.update(builder -> builder
                .index(INDEX_NAME)
                .id("2")
                .doc(poet)
                //如果要修改的文档不存在则新插入
                .docAsUpsert(true), Poet.class);
        log.info(response1.toString());
    }

    /**
     * 新增或修改文档，修改时所有的字段都会覆盖(相当于先删除在新增)
     */
    @Test
    public void createOrUpdateDoc() throws IOException {
        Map<String, Object> doc = new HashMap<>();
        doc.put("age", 33);
        doc.put("name", "李白3");

        //只更新设置的字段
        IndexResponse response = client.index(builder -> builder.index(INDEX_NAME).id("1").document(doc));
        log.info(response.toString());

        Poet poet = new Poet();
        poet.setAge(40);
        poet.setName("杜甫3");
        response = client.index(builder -> builder.index(INDEX_NAME).id("2").document(poet));
        log.info(response.toString());
    }


    /**
     * 批量操作
     */
    @Test
    public void bulk() throws IOException {
        List<BulkOperation> list = new ArrayList<>();

        //批量新增
        for (int i = 0; i < 5; i++) {
            Map<String, Object> doc = new HashMap<>();
            doc.put("age", 30);
            doc.put("name", "李白" + i);
            doc.put("poems", "静夜思");
            doc.put("about", "字太白");
            doc.put("success", "创造了古代浪漫主义文学高峰、歌行体和七绝达到后人难及的高度");
            String id = 10 + i + "";
            list.add(new BulkOperation.Builder().create(builder -> builder.index(INDEX_NAME).id(id).document(doc)).build());
        }
        for (int i = 0; i < 5; i++) {
            Poet poet = new Poet(31, "杜甫" + i, "登高", "字子美", "唐代伟大的现实主义文学作家，唐诗思想艺术的集大成者");
            String id = 20 + i + "";
            list.add(new BulkOperation.Builder().create(builder -> builder.index(INDEX_NAME).id(id).document(poet)).build());
        }

        //批量删除
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(INDEX_NAME).id("1")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(INDEX_NAME).id("2")).build());

        BulkResponse response = client.bulk(builder -> builder.index(INDEX_NAME).operations(list));
        log.info(response.toString());
    }

    /**
     * matchAll全部删除
     */
    @Test
    public void deleteMatchAll() throws IOException {
        DeleteByQueryResponse response = client.deleteByQuery(builder -> builder.index(INDEX_NAME)
                .query(queryBuilder -> queryBuilder
                        .matchAll(matchBuilder -> matchBuilder)));

        log.info(response.toString());
    }

    /**
     * match查询删除
     */
    @Test
    public void deleteMatch() throws IOException {
        DeleteByQueryResponse response = client.deleteByQuery(builder -> builder.index(INDEX_NAME)
                .query(queryBuilder -> queryBuilder
                        .match(matchQueryBuilder -> matchQueryBuilder
                                //使用ik分词器拆分 ”李白 杜甫“ 为 ”李白“和”杜甫“，然后根据之前设定的匹配策略（关键词匹配）查询
                                .field("name").query("李白 杜甫").analyzer("ik_max_word"))));

        log.info(response.toString());
    }

    /**
     * 查询指定index下所有文档
     */
    @Test
    public void getDocAll() throws IOException {
        SearchResponse<Map> response = client.search(builder -> builder.index(INDEX_NAME), Map.class);
        log.info(response.toString());
    }

    /**
     * 按id查询单个文档
     */
    @Test
    public void getDoc() throws IOException {
        GetResponse<Map> response = client.get(builder -> builder.index(INDEX_NAME).id("1"), Map.class);
        if (response.found()) {
            assert response.source() != null;
            log.info(response.source().toString());
        }

        GetResponse<Poet> response2 = client.get(builder -> builder.index(INDEX_NAME).id("2"), Poet.class);
        if (response2.found()) {
            assert response2.source() != null;
            log.info(response2.source().toString());
        }
    }

    /**
     * term/terms查询,对输入内容不做分词处理（完全匹配）
     */
    @Test
    public void searchTerm() throws IOException {
        SearchResponse<Map> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                //使用term完全匹配查询
                                .term(termQueryBuilder -> termQueryBuilder
                                        .field("name").value("李白3")))
                        //设置排序
                        .sort(sortOptionsBuilder -> sortOptionsBuilder
                                .field(fieldSortBuilder -> fieldSortBuilder
                                        //按名称升序排序
                                        .field("name").order(SortOrder.Asc)))
                        //设置返回的属性
                        .source(sourceConfigBuilder -> sourceConfigBuilder
                                .filter(sourceFilterBuilder -> sourceFilterBuilder
                                        //返回属性包括年龄和姓名
                                        .includes("age", "name")))
                        //分页数据
                        .from(0)
                        .size(10)
                , Map.class);
        log.info(response.toString());

        List<FieldValue> words = new ArrayList<>();
        words.add(new FieldValue.Builder().stringValue("李白3").build());
        words.add(new FieldValue.Builder().stringValue("杜甫3").build());
        SearchResponse<Poet> response2 = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                //使用terms多条件完全匹配查询
                                .terms(termsQueryBuilder -> termsQueryBuilder
                                        .field("name")
                                        .terms(termsQueryFieldBuilder -> termsQueryFieldBuilder.value(words))))
                        .source(sourceConfigBuilder -> sourceConfigBuilder
                                .filter(sourceFilterBuilder -> sourceFilterBuilder
                                        //查询除了about以外的字段
                                        .excludes("about")))
                        //分页数据
                        .from(0)
                        .size(10)
                , Poet.class);
        log.info(response2.toString());
    }

    /**
     * range查询,范围查询
     */
    @Test
    public void searchRange() throws IOException {
        SearchResponse<Poet> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .range(rangeQueryBuilder -> rangeQueryBuilder
                                        //查询年龄在 [20,40) 之间的
                                        .field("age").gte(JsonData.of("20")).lt(JsonData.of("40"))))
                , Poet.class);
        log.info(response.toString());
    }

    /**
     * match查询，对输入内容先分词再查询
     * 分词策略使用前面设置的Ik分词器
     */
    @Test
    public void searchMatch() throws IOException {
        SearchResponse<Map> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .match(matchQueryBuilder -> matchQueryBuilder
                                        //以 “思想” 为关键词，使用分词查询查询success字段
                                        .field("success").query("浪漫主义 思想")))
                , Map.class);
        log.info(response.toString());
    }

    /**
     * multi_match查询，可指定多个field
     */
    @Test
    public void searchMultiMatch() throws IOException {
        SearchResponse<Poet> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .multiMatch(multiMatchQueryBuilder -> multiMatchQueryBuilder
                                        //指定about和success为查询字段
                                        .fields("about", "success").query("唐朝")))
                , Poet.class);
        log.info(response.toString());
    }

    /**
     * match_phrase 查询,匹配整个查询字符串
     * match_phrase查询用于查找指定字段中包含指定短语的文档，而且这些词或短语必须按照指定顺序相邻出现
     */
    @Test
    public void searchMatchPhrase() throws IOException {
        SearchResponse<Poet> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .matchPhrase(matchPhraseQueryBuilder -> matchPhraseQueryBuilder.field("success").query("文学作家")))
                , Poet.class);
        log.info(response.toString());
    }

    /**
     * match_all 查询,查询所有
     */
    @Test
    public void searchMatchAll() throws IOException {
        SearchResponse<Poet> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .matchAll(matchAllQueryBuilder -> matchAllQueryBuilder))
                , Poet.class);
        log.info(response.toString());
    }

    /**
     * query_string 查询
     */
    @Test
    public void searchQueryString() throws IOException {
        //类似 match
        SearchResponse<Poet> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .queryString(queryStringQueryBuilder -> queryStringQueryBuilder
                                        .defaultField("success").query("古典文学")))
                , Poet.class);
        log.info(response.toString());

        //类似 multi_match
        response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .queryString(queryStringQueryBuilder -> queryStringQueryBuilder
                                        .fields("about", "success").query("古典文学")))
                , Poet.class);
        log.info(response.toString());

        //类似 match_phrase
        response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .queryString(queryStringQueryBuilder -> queryStringQueryBuilder
                                        .defaultField("success").query("\"文学作家\"")))
                , Poet.class);
        log.info(response.toString());

        //带运算符查询，运算符两边的词不再分词
        //查询同时包含 ”文学“ 和 ”伟大“ 的文档
        response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .queryString(queryStringQueryBuilder -> queryStringQueryBuilder
                                        .fields("success").query("文学 AND 伟大")))
                , Poet.class);
        log.info(response.toString());

        //等同上一个查询
        response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .queryString(queryStringQueryBuilder -> queryStringQueryBuilder
                                        .fields("success").query("文学 伟大").defaultOperator(Operator.And)))
                , Poet.class);
        log.info(response.toString());

        //查询 name 或 success 字段包含"文学"和"伟大"这两个单词，或者包含"李白"这个单词的文档。
        response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .queryString(queryStringQueryBuilder -> queryStringQueryBuilder
                                        .fields("name","success").query("(文学 AND 伟大) OR 高度")))
                , Poet.class);
        log.info(response.toString());
    }

    /**
     * simple_query_string 查询,和query_string类似
     * 不支持AND OR NOT，会当做字符串处理
     * 使用 +替代AND,|替代OR,-替代NOT
     */
    @Test
    public void searchSimpleQueryString() throws IOException {
        //查询同时包含 ”文学“ 和 ”伟大“ 的文档
        SearchResponse<Poet> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .simpleQueryString(simpleQueryStringQueryBuilder -> simpleQueryStringQueryBuilder
                                        .fields("success").query("文学 + 伟大")))
                , Poet.class);
        log.info(response.toString());
    }

    /**
     * 模糊查询
     */
    @Test
    public void searchFuzzy() throws IOException {
        //全文查询时使用模糊参数，先分词再计算模糊选项。
        SearchResponse<Poet> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .match(matchQueryBuilder -> matchQueryBuilder
                                        .field("success").query("思考").fuzziness("1")))
                , Poet.class);
        log.info(response.toString());

        //使用 fuzzy query，对输入不分词，直接计算模糊选项。
        SearchResponse<Poet> response2 = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .fuzzy(fuzzyQueryBuilder ->  fuzzyQueryBuilder
                                        .field("success").fuzziness("1").value("理想")))
                , Poet.class);
        log.info(response2.toString());
    }

    /**
     * bool查询,组合查询
     */
    @Test
    public void searchBool() throws IOException {
        //查询 success 包含 “思想” 且 age 在 [20-40] 之间的文档
        SearchResponse<Poet> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .bool(boolQueryBuilder -> boolQueryBuilder
                                        //必须包含思想
                                        .must(queryBuilder2 -> queryBuilder2
                                                .match(matchQueryBuilder -> matchQueryBuilder
                                                        .field("success").query("思想"))
                                        )
                                        //年龄必须在 [20,40) 之间
                                        .must(queryBuilder2 -> queryBuilder2
                                                .range(rangeQueryBuilder -> rangeQueryBuilder
                                                        .field("age").gte(JsonData.of("20")).lt(JsonData.of("40")))
                                        )
                                )
                        )
                , Poet.class);
        log.info(response.toString());

        //过滤出 success 包含 “思想” 且 age 在 [20,40) 之间的文档，不计算得分
        SearchResponse<Poet> response2 = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .bool(boolQueryBuilder -> boolQueryBuilder
                                        .filter(queryBuilder2 -> queryBuilder2
                                                .match(matchQueryBuilder -> matchQueryBuilder
                                                        .field("success").query("思想"))
                                        )
                                        .filter(queryBuilder2 -> queryBuilder2
                                                .range(rangeQueryBuilder -> rangeQueryBuilder
                                                        .field("age").gte(JsonData.of("20")).lt(JsonData.of("40")))
                                        )
                                )
                        )
                , Poet.class);
        log.info(response2.toString());
    }

    /**
     * aggregations查询,聚合查询
     */
    @Test
    public void searchAggregations() throws IOException {
        //求和
        SearchResponse<Poet> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .aggregations("age_sum", aggregationBuilder -> aggregationBuilder
                                .sum(sumAggregationBuilder -> sumAggregationBuilder
                                        .field("age")))
                , Poet.class);
        log.info(response.toString());

        //类似 select count distinct(age) from poet-index
        response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .aggregations("age_count", aggregationBuilder -> aggregationBuilder
                                .cardinality(cardinalityAggregationBuilder -> cardinalityAggregationBuilder.field("age")))
                , Poet.class);
        log.info(response.toString());

        //数量、最大、最小、平均、求和
        response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .aggregations("age_stats", aggregationBuilder -> aggregationBuilder
                                .stats(statsAggregationBuilder -> statsAggregationBuilder
                                        .field("age")))
                , Poet.class);
        log.info(response.toString());

        //select name,count(*) from poet-index group by name
        response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .aggregations("name_terms", aggregationBuilder -> aggregationBuilder
                                .terms(termsAggregationBuilder -> termsAggregationBuilder
                                        .field("name")))
                , Poet.class);
        log.info(response.toString());

        //select name,age,count(*) from poet-index group by name,age
        response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .aggregations("name_terms", aggregationBuilder -> aggregationBuilder
                                .terms(termsAggregationBuilder -> termsAggregationBuilder
                                        .field("name")
                                )
                                .aggregations("age_terms", aggregationBuilder2 -> aggregationBuilder2
                                        .terms(termsAggregationBuilder -> termsAggregationBuilder
                                                .field("age")
                                        ))
                        )
                , Poet.class);
        log.info(response.toString());

        //类似 select avg(age) from poet-index where name='李白'
        response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .bool(boolQueryBuilder -> boolQueryBuilder
                                        .filter(queryBuilder2 -> queryBuilder2
                                                .term(termQueryBuilder -> termQueryBuilder
                                                        .field("name").value("李白")))))
                        .aggregations("ave_age", aggregationBuilder -> aggregationBuilder
                                .avg(averageAggregationBuilder -> averageAggregationBuilder.field("age")))
                , Poet.class);
        log.info(response.toString());
    }

    /**
     * suggest查询，推荐搜索
     * 在用户输入搜索的过程中，进行自动补全或者纠错。以此来提高搜索文档的匹配精准度，进而提升用户的搜索体验
     * <ul>suggest_mode：搜索推荐的推荐模式，参数值是枚举类：</ul>
     * <li>missing：默认值，仅为不在索引中的词项生成建议词</li>
     * <li>popular：仅返回与搜索词文档词频或文档词频更高的建议词</li>
     * <li>always：根据 建议文本中的词项 推荐 任何匹配的建议词</li>
     */
    @Test
    public void searchSuggest() throws IOException {
        SearchResponse<Poet> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .suggest(suggesterBuilder -> suggesterBuilder
                                .suggesters("success_suggest", fieldSuggesterBuilder -> fieldSuggesterBuilder
                                        .text("思考")
                                        .term(termSuggesterBuilder -> termSuggesterBuilder
                                                .field("success")
                                                .suggestMode(SuggestMode.Always)
                                                .minWordLength(2)
                                        )
                                )
                        )
                , Poet.class);
        log.info(response.toString());
    }

    /**
     * 高亮显示
     */
    @Test
    public void searchHighlight() throws IOException {
        SearchResponse<Poet> response = client.search(searchRequestBuilder -> searchRequestBuilder
                        .index(INDEX_NAME)
                        .query(queryBuilder -> queryBuilder
                                .match(matchQueryBuilder -> matchQueryBuilder
                                        .field("success").query("思想")))
                        .highlight(highlightBuilder -> highlightBuilder
                                //添加查询关键词的高亮标签
                                .preTags("<span color='red'>")
                                .postTags("</span>")
                                .fields("success", highlightFieldBuilder -> highlightFieldBuilder))
                , Poet.class);
        log.info(response.toString());
    }

    /**
     * sql查询，报错
     */
    @Test
    public void searchSql() throws IOException {
        QueryResponse response = client.sql().query(builder -> builder
                .format("json").query("SELECT * FROM \"" + INDEX_NAME + "\" limit 1"));
        log.info(response.toString());
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Poet {
        private Integer age;
        private String name;
        private String poems;
        private String about;
        /**成就*/
        private String success;
    }
}