package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.BookWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 */
public interface BookWordRepository extends JpaRepository<BookWord, BookWord.BookWordId> {
    List<BookWord> findAllByWord(String word);

    Page<BookWord> findAllByBookId(long bookId, Pageable pageable);
}
