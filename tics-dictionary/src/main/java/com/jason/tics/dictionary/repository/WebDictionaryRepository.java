package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.web.WebDictionary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface WebDictionaryRepository extends JpaRepository<WebDictionary, String> {
}
