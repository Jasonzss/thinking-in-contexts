package com.jason.tics.exercise.repository;

import com.jason.tics.exercise.domain.exam.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface PaperRepository extends JpaRepository<Paper, Long> {
}
