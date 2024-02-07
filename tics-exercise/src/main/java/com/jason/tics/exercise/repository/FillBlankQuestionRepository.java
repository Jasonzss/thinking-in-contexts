package com.jason.tics.exercise.repository;

import com.jason.tics.exercise.domain.question.FillBlankQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface FillBlankQuestionRepository extends JpaRepository<FillBlankQuestion, String> {
}
