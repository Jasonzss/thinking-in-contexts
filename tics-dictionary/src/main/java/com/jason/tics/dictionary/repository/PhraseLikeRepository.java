package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.PhraseLike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface PhraseLikeRepository extends JpaRepository<PhraseLike, PhraseLike.PhraseLikeId> {
}
