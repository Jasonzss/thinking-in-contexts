package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.WordBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 */
public interface WordBookRepository extends JpaRepository<WordBook, Long> {

}
