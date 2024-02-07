package com.jason.tics.dictionary.resource;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.security.annotation.SortableEntity;
import com.jason.tics.dictionary.domain.BookWord;
import com.jason.tics.dictionary.domain.WordBook;
import com.jason.tics.dictionary.repository.BookWordRepository;
import com.jason.tics.dictionary.repository.WordBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/dictionary/book")
public class WordBookResource {
    @Autowired
    private WordBookRepository wordBookRepository;
    @Autowired
    private BookWordRepository bookWordRepository;

    @GetMapping("/{id}")
    public ServerResponseEntity<WordBook> getWordBook(@PathVariable long id){
        return ServerResponseEntity.success(wordBookRepository.getById(id));
    }

    @PostMapping
    public ServerResponseEntity<WordBook> addWordBook(@RequestBody @Validated WordBook wordBook){
        return ServerResponseEntity.success(wordBookRepository.save(wordBook));
    }

    @DeleteMapping("/{id}")
    public ServerResponseEntity<Void> deleteWordBook(@PathVariable long id){
        wordBookRepository.deleteById(id);
        return ServerResponseEntity.success();
    }

    @PutMapping("/{id}")
    public ServerResponseEntity<WordBook> updateWordBook(@RequestBody @Validated WordBook wordBook){
        return ServerResponseEntity.success(wordBookRepository.save(wordBook));
    }

    @GetMapping("/{id}/word")
    public ServerResponseEntity<Page<BookWord>> listWords(@PathVariable long id, Pageable pageable){
        return ServerResponseEntity.success(bookWordRepository.findAllByBookId(id, pageable));
    }

    @PostMapping("/{id}/word")
    public ServerResponseEntity<BookWord> addWord(@RequestBody BookWord bookWord){
        return ServerResponseEntity.success(bookWordRepository.save(bookWord));
    }

    @DeleteMapping("/{id}/word/{word}")
    public ServerResponseEntity<BookWord> deleteWord(@PathVariable long id, @PathVariable String word){
        bookWordRepository.deleteById(new BookWord.BookWordId(word, id));
        return ServerResponseEntity.success();
    }
}
