package com.jason.tics.recommend.service.impl;

import com.jason.tics.api.content.bo.ContentType;
import com.jason.tics.recommend.domain.preference.UserMediaPreferences;
import com.jason.tics.recommend.domain.recommender.ContentRecommender;
import com.jason.tics.recommend.domain.MediaPreferenceAnalyzer;
import com.jason.tics.recommend.service.RecommendService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Jason
 */
@Service
@Slf4j
@Data
public class DefaultRecommendService implements RecommendService, ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;

    @Autowired
    private MediaPreferenceAnalyzer mediaPreferenceAnalyzer;

    /**
     * key {@link UserMediaPreferences.UserMediaPreferencesId#getMediaType()} ()}
     */
    @Autowired
    private Map<String, ContentRecommender> contentRecommenders;

    public Set<String> recommend(long uid, @Size(max = 50, min = 1) int howMany) {
        SortedMap<Float, String> idMap = new TreeMap<>();

        List<UserMediaPreferences> mp = mediaPreferenceAnalyzer.listPreferences(uid);
        AtomicReference<Double> totalPrefer = new AtomicReference<>(0.0);
        mp.forEach(p -> totalPrefer.updateAndGet(v -> v + p.getPreference()));

        for (UserMediaPreferences p : mp) {
            ContentRecommender recommender;
            if ((recommender = contentRecommenders.get(p.getPreferencesId().getMediaType())) != null) {
                int n = (int) Math.round(howMany * (p.getPreference()/totalPrefer.get()));
                try {
                    List<RecommendedItem> items = recommender.recommend(uid, n);
                    for (RecommendedItem item : items) {
                        idMap.put(item.getValue(), ContentType.getPrefix(p.getPreferencesId().getMediaType())+item.getItemID());
                    }
                } catch (TasteException e) {
                    log.error("recommend fail", e);
                    e.printStackTrace();
                }
            }
        }

        return new HashSet<>(idMap.values());
    }

    @Override
    public void afterPropertiesSet() {
        this.contentRecommenders = applicationContext.getBeansOfType(ContentRecommender.class);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
