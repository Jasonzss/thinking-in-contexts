package com.jason.tics.learn.repository;

import com.jason.tics.learn.domain.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jason
 */
public interface CardsRepository extends JpaRepository<Cards, String> {
    Optional<Cards> findByFrontAndUid(String front, long uid);

    List<Cards> findAllByUid(long uid);
}