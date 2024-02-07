package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.Phrase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface PhraseRepository extends JpaRepository<Phrase, Long> {
    Page<Phrase> findByContentContainingOrderByLikeNum(String content, Pageable pageable);
}
