package com.jason.tics.recommend.domain.repository;

import com.jason.tics.common.jpa.repository.TicsRepository;
import com.jason.tics.recommend.domain.preference.AudioTastePreferences;

/**
 * @author Jason
 */
public interface AudioTastePreferencesRepository extends TicsRepository<AudioTastePreferences, Long> {
    void deleteAllByItemId(long itemId);
}
