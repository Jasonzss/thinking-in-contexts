package com.jason.tics.recommend.domain.repository;

import com.jason.tics.common.jpa.repository.TicsRepository;
import com.jason.tics.recommend.domain.preference.EssayTastePreferences;

/**
 * @author Jason
 */
public interface EssayTastePreferencesRepository extends TicsRepository<EssayTastePreferences, Long> {
    void deleteAllByItemId(long itemId);
}
