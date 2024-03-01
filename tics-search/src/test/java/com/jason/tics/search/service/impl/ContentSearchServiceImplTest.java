package com.jason.tics.search.service.impl;

import cn.hutool.core.date.DateUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import com.jason.tics.api.content.bo.AudioPostBo;
import com.jason.tics.api.content.bo.EssayPostBo;
import com.jason.tics.api.content.bo.VideoPostBo;
import com.jason.tics.api.search.bo.SearchResult;
import com.jason.tics.search.SearchApplication;
import com.jason.tics.search.constant.EsConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 命令行运行此命令查看ik分词器的分词功能
 * curl -X GET "http://localhost:9200/_analyze" -H "Content-Type: application/json" -d "{\"analyzer\": \"ik_max_word\", \"text\": \"Hope we can still get it on And 99% of the painting seems to be centered around one general subject\"}"
 * ik分词器分英文时不会把一些基础词汇分出来，例如 it of the 等常用词汇
 * @author Jason
 */
@SpringBootTest(classes = SearchApplication.class)
@Slf4j
public class ContentSearchServiceImplTest {
    @Autowired
    private ContentSearchServiceImpl contentSearchService;
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @BeforeEach
    public void init() throws IOException {
        List<BulkOperation> list = new ArrayList<>();

        //批量新增
        //内容复制自reddit和b乎

        //audio
        AudioPostBo a1 = AudioPostBo.AudioPostBoBuilder.anAudioPostBo()
                .audioId("a1")
                .audioUrl("url")
                .authorId(114514L)
                .coverImageUrl("imageUrl")
                .quantityOfViews(25L)
                .createTime(DateUtil.yesterday())
                .title("Sorry, what hint?")
                .introduction("That’s XCOM baby")
                .build();
        AudioPostBo a2 = AudioPostBo.AudioPostBoBuilder.anAudioPostBo()
                .audioId("a2")
                .audioUrl("url")
                .authorId(114514L)
                .coverImageUrl("imageUrl")
                .quantityOfViews(25L)
                .createTime(DateUtil.yesterday())
                .title("I think the AI got it wrong")
                .introduction("And 99% of the painting seems to be centered around one general subject.")
                .build();
        AudioPostBo a3 = AudioPostBo.AudioPostBoBuilder.anAudioPostBo()
                .audioId("a3")
                .audioUrl("url")
                .authorId(114514L)
                .coverImageUrl("imageUrl")
                .quantityOfViews(25L)
                .createTime(DateUtil.yesterday())
                .title("It is what it is")
                .introduction("And that's a sad fact. I thought at least in some universe I'd succeeded")
                .build();
        AudioPostBo a4 = AudioPostBo.AudioPostBoBuilder.anAudioPostBo()
                .audioId("a4")
                .audioUrl("url")
                .authorId(114514L)
                .coverImageUrl("imageUrl")
                .quantityOfViews(25L)
                .createTime(DateUtil.yesterday())
                .title("写小说的时候，有没有比死亡更能升华角色的手段？")
                .introduction("可以去看一看作家马原的《关于小说结局的十三种方式》，十三种结局方式分别是死、大团圆、诗意、揭谜、解嘲、有去无归、梦魇、循环往复、得而复失、走出叙事人视野、以死亡来终止、歪打正着以及迷失。")
                .build();
        AudioPostBo a5 = AudioPostBo.AudioPostBoBuilder.anAudioPostBo()
                .audioId("a5")
                .audioUrl("url")
                .authorId(114514L)
                .coverImageUrl("imageUrl")
                .quantityOfViews(25L)
                .createTime(DateUtil.yesterday())
                .title("明明可以通过调用API来操作数据库，那么SQL还有什么存在的意义吗?")
                .introduction("这完全是历史的眼泪，如果json在那个时候出现也就没sql啥事了。\n" +
                        "\n" +
                        "你看新兴的nosql，比如MongoDB这些，全是json描述操作，或者类似json的东西（graphql）")
                .build();

        // essay
        EssayPostBo e1 = EssayPostBo.EssayPostBoBuilder.anEssayPostBo()
                .essayUrl("url")
                .coverImageUrl("imageUrl")
                .essayId("e1")
                .authorId(114514L)
                .quantityOfViews(0L)
                .title("Astronomers narrow down where 'Planet Nine' could be hiding by playing massive game of 'connect the dots' | Live Science")
                .createTime(DateUtil.yesterday())
                .build();
        EssayPostBo e2 = EssayPostBo.EssayPostBoBuilder.anEssayPostBo()
                .essayUrl("url")
                .coverImageUrl("imageUrl")
                .essayId("e2")
                .authorId(114514L)
                .quantityOfViews(0L)
                .title("It turns out that Odysseus landed on the Moon without any altimetry data")
                .createTime(DateUtil.yesterday())
                .build();
        EssayPostBo e3 = EssayPostBo.EssayPostBoBuilder.anEssayPostBo()
                .essayUrl("url")
                .coverImageUrl("imageUrl")
                .essayId("e3")
                .authorId(114514L)
                .quantityOfViews(0L)
                .title("Spent 6 months using Anki. Fell into a bout of depression and stopped using it for the next 6 months. I now have ~1500 cards due. How do I deal with this? Will it take me the same amount of time to relearn everything again? Want to make the most efficient use of time as I'm also applying for jobs.")
                .createTime(DateUtil.yesterday())
                .build();
        EssayPostBo e4 = EssayPostBo.EssayPostBoBuilder.anEssayPostBo()
                .essayUrl("url")
                .coverImageUrl("imageUrl")
                .essayId("e4")
                .authorId(114514L)
                .quantityOfViews(0L)
                .title("THIS MEME BECAME PART CANON AT THE FOOD EVENT")
                .createTime(DateUtil.yesterday())
                .build();
        EssayPostBo e5 = EssayPostBo.EssayPostBoBuilder.anEssayPostBo()
                .essayUrl("url")
                .coverImageUrl("imageUrl")
                .essayId("e5")
                .authorId(114514L)
                .quantityOfViews(0L)
                .title("朱元璋姓朱 那明朝的猪叫啥？")
                .createTime(DateUtil.yesterday())
                .build();
        EssayPostBo e6 = EssayPostBo.EssayPostBoBuilder.anEssayPostBo()
                .essayUrl("url")
                .coverImageUrl("imageUrl")
                .essayId("e6")
                .authorId(114514L)
                .quantityOfViews(0L)
                .title("You've Been Bamboozled")
                .createTime(DateUtil.yesterday())
                .build();

        // video
        VideoPostBo v1 = VideoPostBo.VideoPostBoBuilder.aVideoPostBo()
                .videoUrl("url")
                .coverImageUrl("imageUrl")
                .createTime(DateUtil.yesterday())
                .videoId("v1")
                .authorId(114514L)
                .quantityOfViews(15L)
                .title("How low can Ubisoft go?")
                .introduction("Why would Assassin's Creed even need a damn battle pass?")
                .build();
        VideoPostBo v2 = VideoPostBo.VideoPostBoBuilder.aVideoPostBo()
                .videoUrl("url")
                .coverImageUrl("imageUrl")
                .createTime(DateUtil.yesterday())
                .videoId("v2")
                .authorId(114514L)
                .quantityOfViews(15L)
                .title("Remember gentlemen...")
                .introduction("Always remember to bring your M249 .5.56×45mm before you play paintball!")
                .build();
        VideoPostBo v3 = VideoPostBo.VideoPostBoBuilder.aVideoPostBo()
                .videoUrl("url")
                .coverImageUrl("imageUrl")
                .createTime(DateUtil.yesterday())
                .videoId("v3")
                .authorId(114514L)
                .quantityOfViews(15L)
                .title("You guys seriously think that way?")
                .introduction("As per reddit's Wikipedia page. It was founded on 2005, now is 2024. Its a history of 19 years millions of users logging in posting, writing comments, replying to comments.\n" +
                        "\n" +
                        "The amount of data for training an AI quite sufficient here.")
                .build();
        VideoPostBo v4 = VideoPostBo.VideoPostBoBuilder.aVideoPostBo()
                .videoUrl("url")
                .coverImageUrl("imageUrl")
                .createTime(DateUtil.yesterday())
                .videoId("v4")
                .authorId(114514L)
                .quantityOfViews(15L)
                .title("大陆电影译名最烂的是哪一部？")
                .introduction("为什么电脑不做打电话功能？")
                .build();
        VideoPostBo v5 = VideoPostBo.VideoPostBoBuilder.aVideoPostBo()
                .videoUrl("url")
                .coverImageUrl("imageUrl")
                .createTime(DateUtil.yesterday())
                .videoId("v5")
                .authorId(114514L)
                .quantityOfViews(15L)
                .title("Hope we can still get it on")
                .introduction("Smash, next question")
                .build();
        VideoPostBo v6 = VideoPostBo.VideoPostBoBuilder.aVideoPostBo()
                .videoUrl("url")
                .coverImageUrl("imageUrl")
                .createTime(DateUtil.yesterday())
                .videoId("v6")
                .authorId(114514L)
                .quantityOfViews(15L)
                .title("If you wanna fuck up the algorithm, do it the right way")
                .introduction("Watch google ctrl f replace the word bazinga in the entire training data.")
                .build();
        VideoPostBo v7 = VideoPostBo.VideoPostBoBuilder.aVideoPostBo()
                .videoUrl("url")
                .coverImageUrl("imageUrl")
                .createTime(DateUtil.yesterday())
                .videoId("v7")
                .authorId(114514L)
                .quantityOfViews(15L)
                .title("开发了一个App，上线之后一个用户也没有怎么办?")
                .introduction("要是你这软件都有人愿意用，那我们这些做UI的都没有饭吃了。")
                .build();
        VideoPostBo v8 = VideoPostBo.VideoPostBoBuilder.aVideoPostBo()
                .videoUrl("url")
                .coverImageUrl("imageUrl")
                .createTime(DateUtil.yesterday())
                .videoId("v8")
                .authorId(114514L)
                .quantityOfViews(15L)
                .title("No hate to indie devs but it’s over done")
                .introduction("If you thought that was bad, the amount of Indie Rougelikes and Metroidvanias are absurd.")
                .build();

        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(a1).docAsUpsert(true)).index(EsConstant.AUDIO_INDEX).id("a1")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(a2).docAsUpsert(true)).index(EsConstant.AUDIO_INDEX).id("a2")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(a3).docAsUpsert(true)).index(EsConstant.AUDIO_INDEX).id("a3")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(a4).docAsUpsert(true)).index(EsConstant.AUDIO_INDEX).id("a4")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(a5).docAsUpsert(true)).index(EsConstant.AUDIO_INDEX).id("a5")).build());

        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(e1).docAsUpsert(true)).index(EsConstant.ESSAY_INDEX).id("e1")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(e2).docAsUpsert(true)).index(EsConstant.ESSAY_INDEX).id("e2")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(e3).docAsUpsert(true)).index(EsConstant.ESSAY_INDEX).id("e3")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(e4).docAsUpsert(true)).index(EsConstant.ESSAY_INDEX).id("e4")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(e5).docAsUpsert(true)).index(EsConstant.ESSAY_INDEX).id("e5")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(e6).docAsUpsert(true)).index(EsConstant.ESSAY_INDEX).id("e6")).build());

        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(v1).docAsUpsert(true)).index(EsConstant.VIDEO_INDEX).id("v1")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(v2).docAsUpsert(true)).index(EsConstant.VIDEO_INDEX).id("v2")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(v3).docAsUpsert(true)).index(EsConstant.VIDEO_INDEX).id("v3")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(v4).docAsUpsert(true)).index(EsConstant.VIDEO_INDEX).id("v4")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(v5).docAsUpsert(true)).index(EsConstant.VIDEO_INDEX).id("v5")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(v6).docAsUpsert(true)).index(EsConstant.VIDEO_INDEX).id("v6")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(v7).docAsUpsert(true)).index(EsConstant.VIDEO_INDEX).id("v7")).build());
        list.add(new BulkOperation.Builder().update(b -> b.action(a -> a.doc(v8).docAsUpsert(true)).index(EsConstant.VIDEO_INDEX).id("v8")).build());

        BulkResponse res = elasticsearchClient.bulk(builder -> builder.operations(list));
        log.info("插入：{}", res);
    }

    @Test
    public void  testSearchAll() throws IOException {

        SearchResponse<AudioPostBo> response = elasticsearchClient
                .search(builder -> builder.index(EsConstant.AUDIO_INDEX), AudioPostBo.class);
        log.info("结果0：{}", response);

        SearchResponse<EssayPostBo> re = elasticsearchClient
                .search(builder -> builder.index(EsConstant.ESSAY_INDEX), EssayPostBo.class);
        log.info("结果0：{}", re);

        SearchResponse<VideoPostBo> r = elasticsearchClient
                .search(builder -> builder.index(EsConstant.VIDEO_INDEX), VideoPostBo.class);
        log.info("结果0：{}", r);


        SearchResponse<VideoPostBo> search = elasticsearchClient.search(builder -> builder
                .index(EsConstant.VIDEO_INDEX)
                .query(b -> b.match(matchBuilder -> matchBuilder
                        .field(EsConstant.TITLE).query("App think that way"))), VideoPostBo.class);
        log.info("结果1：{}", search);

        SearchResult<?> res = contentSearchService.searchAll("App think that way", 1, 5,
                EsConstant.AUDIO_INDEX, EsConstant.VIDEO_INDEX, EsConstant.ESSAY_INDEX);
        log.info("结果2：{}", res);
    }

    @AfterEach
    public void destroy() throws IOException {
        List<BulkOperation> list = new ArrayList<>();

        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.AUDIO_INDEX).id("a1")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.AUDIO_INDEX).id("a2")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.AUDIO_INDEX).id("a3")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.AUDIO_INDEX).id("a4")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.AUDIO_INDEX).id("a5")).build());

        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.ESSAY_INDEX).id("e1")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.ESSAY_INDEX).id("e2")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.ESSAY_INDEX).id("e3")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.ESSAY_INDEX).id("e4")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.ESSAY_INDEX).id("e5")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.ESSAY_INDEX).id("e6")).build());

        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.VIDEO_INDEX).id("v1")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.VIDEO_INDEX).id("v2")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.VIDEO_INDEX).id("v3")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.VIDEO_INDEX).id("v4")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.VIDEO_INDEX).id("v5")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.VIDEO_INDEX).id("v6")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.VIDEO_INDEX).id("v7")).build());
        list.add(new BulkOperation.Builder().delete(builder -> builder.index(EsConstant.VIDEO_INDEX).id("v8")).build());

        BulkResponse res = elasticsearchClient.bulk(builder -> builder.operations(list));
        log.info("删除：{}", res);
    }
}
