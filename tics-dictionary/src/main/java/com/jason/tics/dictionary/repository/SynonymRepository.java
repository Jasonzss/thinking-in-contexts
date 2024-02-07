package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.extension.Synonym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 */
public interface SynonymRepository extends JpaRepository<Synonym, Synonym.SynonymsId> {
    List<Synonym> findAllByOneEqualsOrTwoEquals(String one, String two);
}
