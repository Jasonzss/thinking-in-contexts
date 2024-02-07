package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.web.WebTranslation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 */
public interface WebTranslationRepository extends JpaRepository<WebTranslation, Long> {
    List<WebTranslation> getWebTranslationByTargetEquals(String target);
}
