package com.jason.tics.recommend.domain.recommender;

import com.jason.tics.recommend.domain.Preferences;
import org.apache.mahout.cf.taste.recommender.Recommender;

/**
 * @author Jason
 */
public interface ContentRecommender extends Recommender {
    void setPreference(long userID, long itemID, Preferences preference);
}
