package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.ExampleLike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface ExampleLikeRepository extends JpaRepository<ExampleLike, ExampleLike.ExampleLikeId> {
    int countByExampleIdAndIsLikeGreaterThanEqual(long exampleId, boolean isLike);

    int countByExampleIdAndIsLikeLessThanEqual(long exampleId, boolean isLike);
}
