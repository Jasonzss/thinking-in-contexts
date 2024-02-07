package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.DomainTranslation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 */
public interface DomainTranslationRepository extends JpaRepository<DomainTranslation, Long> {
    List<DomainTranslation> findAllByTargetContaining(String target);

    List<DomainTranslation> findAllByTargetEquals(String target);
}
