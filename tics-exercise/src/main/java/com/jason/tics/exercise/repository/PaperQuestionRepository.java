package com.jason.tics.exercise.repository;

import com.jason.tics.exercise.domain.PaperQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface PaperQuestionRepository extends JpaRepository<PaperQuestion, Long> {
}
