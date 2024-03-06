package com.jason.tics.store.repository;

import com.jason.tics.store.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    void deleteByName(String name);
}
