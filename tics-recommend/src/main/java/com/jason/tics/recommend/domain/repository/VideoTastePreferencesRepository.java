package com.jason.tics.recommend.domain.repository;

import com.jason.tics.common.jpa.repository.TicsRepository;
import com.jason.tics.recommend.domain.preference.VideoTastePreferences;

/**
 * @author Jason
 */
public interface VideoTastePreferencesRepository extends TicsRepository<VideoTastePreferences, Long> {
    void deleteAllByItemId(long itemId);
}
