package com.jason.tics.exercise.repository;

import com.jason.tics.exercise.domain.question.ChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface ChoiceQuestionRepository extends JpaRepository<ChoiceQuestion, String> {

}
