package com.jason.tics.recommend.domain;

import com.jason.tics.recommend.domain.preference.UserMediaPreferences;

import java.util.List;

/**
 * @author Jason
 */
public interface MediaPreferenceAnalyzer {
    List<UserMediaPreferences> listPreferences(long uid);
}
