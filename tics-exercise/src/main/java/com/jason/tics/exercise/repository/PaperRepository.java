package com.jason.tics.exercise.repository;

import com.jason.tics.exercise.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface PaperRepository extends JpaRepository<Paper, Long> {
}
