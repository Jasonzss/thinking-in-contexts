package com.jason.tics.exercise.repository;

import com.jason.tics.exercise.domain.PaperAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface PaperAnswerRepository extends JpaRepository<PaperAnswer, Long> {
}
