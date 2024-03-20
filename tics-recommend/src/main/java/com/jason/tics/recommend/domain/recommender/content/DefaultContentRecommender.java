package com.jason.tics.recommend.domain.recommender.content;

import com.jason.tics.recommend.domain.Preferences;
import com.jason.tics.recommend.domain.recommender.ContentRecommender;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;

/**
 * @author Jason
 */
@Data
@Slf4j
public class DefaultContentRecommender implements ContentRecommender {
    private Recommender recommender;

//    public static final String DEFAULT_PREFERENCE_TABLE = "content_taste_preferences";
    public static final String DEFAULT_USER_ID_COLUMN = "user_id";
    public static final String DEFAULT_ITEM_ID_COLUMN = "item_id";
    public static final String DEFAULT_PREFERENCE_COLUMN = "preference";
    public static final String DEFAULT_PREFERENCE_TIME_COLUMN = "timestamp";

    public DefaultContentRecommender(DataSource dataSource, String preferenceTable)  {
        try {
            //连接到mysql数据模型
            DataModel dataModel = new MySQLJDBCDataModel(
                    dataSource,
                    preferenceTable,
                    DEFAULT_USER_ID_COLUMN,
                    DEFAULT_ITEM_ID_COLUMN,
                    DEFAULT_PREFERENCE_COLUMN,
                    DEFAULT_PREFERENCE_TIME_COLUMN
            );
            //定义用户相似度策略
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            //定义邻居（邻居：具有相似性行为偏好的用户）
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
            //使用以上三者手动创建推荐引擎
            Recommender recommender
                    = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

            //添加缓存层
            this.recommender = new CachingRecommender(recommender);
        } catch (TasteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RecommendedItem> recommend(long userID, int howMany)  {
        try {
            return recommender.recommend(userID, howMany);
        } catch (TasteException e) {
            log.error("mahout recommend failed", e);
        }

        return null;
    }

    @Override
    public List<RecommendedItem> recommend(long userID, int howMany, boolean includeKnownItems)  {
        try {
            return recommender.recommend(userID, howMany, includeKnownItems);
        } catch (TasteException e) {
            log.error("mahout recommend failed", e);
        }
        return null;
    }

    @Override
    public List<RecommendedItem> recommend(long userID, int howMany, IDRescorer rescorer)  {
        try {
            return recommender.recommend(userID, howMany, rescorer);
        } catch (TasteException e) {
            log.error("mahout recommend failed", e);
        }
        return null;
    }

    @Override
    public List<RecommendedItem> recommend(long userID, int howMany, IDRescorer rescorer, boolean includeKnownItems)  {
        try {
            return recommender.recommend(userID, howMany, rescorer, includeKnownItems);
        } catch (TasteException e) {
            log.error("mahout recommend failed", e);
        }
        return null;
    }

    /**
     * 估计偏好
     */
    @Override
    public float estimatePreference(long userID, long itemID)  {
        try {
            return recommender.estimatePreference(userID, itemID);
        } catch (TasteException e) {
            log.error("mahout recommend failed", e);
        }
        return -1;
    }

    @Override
    public void setPreference(long userID, long itemID, float value)  {
        try {
            recommender.setPreference(userID, itemID, value);
        } catch (TasteException e) {
            log.error("mahout recommend failed", e);
        }
    }

    @Override
    public void removePreference(long userID, long itemID)  {
        try {
            recommender.removePreference(userID, itemID);
        } catch (TasteException e) {
            log.error("mahout remove preference failed", e);
        }
    }

    @Override
    public DataModel getDataModel() {
        return recommender.getDataModel();
    }

    @Override
    public void refresh(Collection<Refreshable> alreadyRefreshed) {
        recommender.refresh(alreadyRefreshed);
    }

    @Override
    public void setPreference(long userID, long itemID, Preferences preference) {
        try {
            recommender.setPreference(userID, itemID, preference.getValue());
        } catch (TasteException e) {
            log.error("mahout set preference failed", e);
        }
    }
}
