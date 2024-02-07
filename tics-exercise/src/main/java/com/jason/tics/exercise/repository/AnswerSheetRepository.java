package com.jason.tics.exercise.repository;

import com.jason.tics.exercise.domain.AnswerSheet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface AnswerSheetRepository extends JpaRepository<AnswerSheet, Long> {
}
