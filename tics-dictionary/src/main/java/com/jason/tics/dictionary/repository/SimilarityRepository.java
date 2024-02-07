package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.extension.Similarity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 */
public interface SimilarityRepository extends JpaRepository<Similarity, Similarity.SimilarityId> {
    List<Similarity> findAllByOneEqualsOrTwoEquals(String one, String two);
}
