package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface ExampleRepository extends JpaRepository<Example, Long> {
    Page<Example> findBySentenceContaining(String query, Pageable pageable);
}
