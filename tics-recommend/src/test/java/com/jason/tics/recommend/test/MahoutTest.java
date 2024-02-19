package com.jason.tics.recommend.test;

import com.jason.tics.recommend.RecommendApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Jason
 */
@SpringBootTest(classes = RecommendApplication.class)
@Slf4j
public class MahoutTest {
    @Autowired
    private DataSource dataSource;
    DataModel dataModel;

    @BeforeEach
    public void init(){
        //首先定义数据模型，存取用户的偏好数据
        dataModel = new MySQLJDBCDataModel(dataSource);
    }

    @Test
    public void test() throws TasteException {
        //定义用户相似度策略
        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
        //定义邻居（邻居：具有相似性行为偏好的用户）
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
        //使用以上三者手动创建推荐引擎
        Recommender recommender
                = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

        //添加缓存层
        CachingRecommender cachingRecommender = new CachingRecommender(recommender);

        List<RecommendedItem> recommend = cachingRecommender.recommend(1L, 5);
        log.info("推荐列表："+recommend.toString());
    }

    @Test
    public void testEvaluator() throws TasteException {
        //使用平均绝对差值获得评分
        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
        //用RecommenderBuilder构建推荐引擎
        RecommenderBuilder recommenderBuilder = model -> {
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(4, similarity, model);
            return new GenericUserBasedRecommender(model, neighborhood, similarity);
        };

        // Use 70% of the data to train; test using the other 30%.
        double score = evaluator.evaluate(recommenderBuilder,
                null, dataModel, 0.7, 1.0);
        log.info("推荐引擎评分："+score);
    }

    @Test
    public void testIRStatsEvaluator() throws TasteException {
        RecommenderIRStatsEvaluator statsEvaluator = new GenericRecommenderIRStatsEvaluator();
        // Build the same recommender for testing that we did last time:
        RecommenderBuilder recommenderBuilder = model -> {
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(4, similarity, model);
            return new GenericUserBasedRecommender(model, neighborhood, similarity);
        };

        // 计算推荐4个结果时的查准率和召回率
        IRStatistics stats = statsEvaluator
                .evaluate(recommenderBuilder,null, dataModel, null, 4,
                GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD,1.0);
        log.info("查准率："+stats.getPrecision());
        log.info("召回率："+stats.getRecall());
    }
}
