package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.extension.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface StageRepository extends JpaRepository<Stage, String> {
}
