package com.jason.tics.learn.repository;

import com.jason.tics.learn.domain.pojo.WordFsrs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 */
public interface WordFsrsRepository extends JpaRepository<WordFsrs, String> {
    List<WordFsrs> findAllByUid(long uids);

    List<WordFsrs> findAllByFront(Iterable<String> front);
}
