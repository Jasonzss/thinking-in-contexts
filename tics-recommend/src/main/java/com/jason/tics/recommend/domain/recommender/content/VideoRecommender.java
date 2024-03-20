package com.jason.tics.recommend.domain.recommender.content;

import com.jason.tics.recommend.domain.Preferences;
import com.jason.tics.recommend.domain.recommender.ContentRecommender;
import lombok.Getter;
import lombok.Setter;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;

/**
 * @author Jason
 */
@Component("video")
public class VideoRecommender implements ContentRecommender {

    public static final String DEFAULT_PREFERENCE_TABLE = "video_taste_preferences";

    @Getter
    @Setter
    private DefaultContentRecommender recommender;

    @Autowired
    public VideoRecommender(DataSource dataSource)  {
        this.recommender = new DefaultContentRecommender(dataSource, DEFAULT_PREFERENCE_TABLE);
    }

    @Override
    public List<RecommendedItem> recommend(long userID, int howMany)  {
        return recommender.recommend(userID, howMany);
    }

    @Override
    public List<RecommendedItem> recommend(long userID, int howMany, boolean includeKnownItems)  {
        return recommender.recommend(userID, howMany, includeKnownItems);
    }

    @Override
    public List<RecommendedItem> recommend(long userID, int howMany, IDRescorer rescorer)  {
        return recommender.recommend(userID, howMany, rescorer);
    }

    @Override
    public List<RecommendedItem> recommend(long userID, int howMany, IDRescorer rescorer, boolean includeKnownItems)  {
        return recommender.recommend(userID, howMany, rescorer, includeKnownItems);
    }

    @Override
    public float estimatePreference(long userID, long itemID)  {
        return recommender.estimatePreference(userID, itemID);
    }

    @Override
    public void setPreference(long userID, long itemID, float value)  {
        recommender.setPreference(userID, itemID, value);
    }

    @Override
    public void removePreference(long userID, long itemID)  {
        recommender.removePreference(userID, itemID);
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
    public void setPreference(long userID, long itemID, Preferences preference)  {
        recommender.setPreference(userID, itemID, preference);
    }
}
