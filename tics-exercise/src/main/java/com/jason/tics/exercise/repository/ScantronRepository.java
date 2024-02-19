package com.jason.tics.exercise.repository;

import com.jason.tics.exercise.domain.exam.Scantron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Jason
 */
public interface ScantronRepository extends JpaRepository<Scantron, Long> {
    Optional<Scantron> findByUidAndIsFinishedAndPaperId(long uid, boolean isFinished, long paperId);
}
