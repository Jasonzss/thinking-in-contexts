package com.jason.tics.recommend.domain.repository;

import com.jason.tics.common.jpa.repository.TicsRepository;
import com.jason.tics.recommend.domain.preference.UserMediaPreferences;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Jason
 */
public interface UserMediaPreferencesRepository extends TicsRepository<UserMediaPreferences, UserMediaPreferences.UserMediaPreferencesId> {
    @Query(value = "select * from tics_recommend.user_media_preferences where uid = ?1", nativeQuery = true)
    List<UserMediaPreferences> findAllByUid(long uid);
}
