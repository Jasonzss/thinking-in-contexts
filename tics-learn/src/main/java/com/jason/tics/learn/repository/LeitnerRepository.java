package com.jason.tics.learn.repository;

import com.jason.tics.learn.domain.Leitner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface LeitnerRepository extends JpaRepository<Leitner, String> {
}
