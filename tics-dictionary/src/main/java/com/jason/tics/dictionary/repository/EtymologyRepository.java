package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.extension.Etymology;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface EtymologyRepository extends JpaRepository<Etymology, String> {
}
