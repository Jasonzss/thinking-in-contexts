package com.jason.tics.learn.repository;

import com.jason.tics.learn.domain.Fsrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Jason
 */
public interface FsrsRepository extends JpaRepository<Fsrs, String> {
    @Query(value = "select * from tics_learn.fsrs f " +
            "left join tics_learn.cards c on (c.uid = ?1 and c.card_uuid = f.card_uuid) " +
            "where f.state = ?2", nativeQuery = true)
    List<Fsrs> findAllByUidAndState(long uid, String state);
}
