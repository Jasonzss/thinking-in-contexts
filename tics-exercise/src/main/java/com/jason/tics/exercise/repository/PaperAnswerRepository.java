package com.jason.tics.exercise.repository;

import com.jason.tics.exercise.domain.exam.PaperAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Jason
 */
public interface PaperAnswerRepository extends JpaRepository<PaperAnswer, Long> {
    Optional<PaperAnswer> findByScantronIdAndIdx(long scantronId, int idx);
}
