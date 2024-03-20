package com.jason.tics.recommend.domain.media;

import com.jason.tics.api.content.bo.ContentType;
import com.jason.tics.recommend.domain.MediaPreferenceAnalyzer;
import com.jason.tics.recommend.domain.preference.UserMediaPreferences;
import com.jason.tics.recommend.domain.repository.UserMediaPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason
 */
@Component
public class AccumulateMediaPreferenceAnalyzer implements MediaPreferenceAnalyzer {
    @Autowired
    private UserMediaPreferencesRepository mediaPreferencesRepository;

    @Override
    public List<UserMediaPreferences> listPreferences(long uid) {
        List<UserMediaPreferences> preferences = mediaPreferencesRepository.findAllByUid(uid);
        if (preferences.size() == 0){
            //偏好创建是在第一次查询时创建的
            List<UserMediaPreferences> l = new ArrayList<>();
            l.add(new UserMediaPreferences(UserMediaPreferences.id(uid, ContentType.AUDIO.getName()), 1.0));
            l.add(new UserMediaPreferences(UserMediaPreferences.id(uid, ContentType.ESSAY.getName()), 1.0));
            l.add(new UserMediaPreferences(UserMediaPreferences.id(uid, ContentType.VIDEO.getName()), 1.0));
            preferences = mediaPreferencesRepository.saveAll(l);
        }
        return preferences;
    }

    public void accumulate(long uid, String mediaType){
        UserMediaPreferences p = mediaPreferencesRepository
                .getById(UserMediaPreferences.id(uid, mediaType));
        mediaPreferencesRepository.save(p.updatePreference(1));
    }
}
