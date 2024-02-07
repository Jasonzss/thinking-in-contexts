package com.jason.tics.exercise.repository;

import com.jason.tics.exercise.domain.WordLearningTarget;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface WordLearningTargetRepository extends JpaRepository<WordLearningTarget, Long> {
}
