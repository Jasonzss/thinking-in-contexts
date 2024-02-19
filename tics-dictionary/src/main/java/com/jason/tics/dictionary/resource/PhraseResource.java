package com.jason.tics.dictionary.resource;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.jpa.utils.JpaCrudUtil;
import com.jason.tics.common.security.annotation.SortableEntity;
import com.jason.tics.dictionary.domain.Phrase;
import com.jason.tics.dictionary.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author Jason
 */
@RestController
@RequestMapping("/dictionary/phrase")
public class PhraseResource {
    @Autowired
    private PhraseRepository phraseRepository;

    @GetMapping("/search")
    public ServerResponseEntity<Page<Phrase>> searchPhrase(@RequestParam String query,
                                                           @SortableEntity(entity = Phrase.class) Pageable pageable){
        return ServerResponseEntity.success(phraseRepository.findByContentContainingOrderByLikeNum(query, pageable));
    }

    @GetMapping("/{id}")
    public ServerResponseEntity<Phrase> getPhrase(@PathVariable long id){
        return ServerResponseEntity.success(phraseRepository.getById(id));
    }

    @PostMapping
    public ServerResponseEntity<Phrase> addPhrase(@RequestBody @Validated Phrase phrase){
        return ServerResponseEntity.success(phraseRepository.save(phrase));
    }

    @DeleteMapping("/{id}")
    public ServerResponseEntity<Void> deletePhrase(@PathVariable long id){
        phraseRepository.deleteById(id);
        return ServerResponseEntity.success();
    }

    @PutMapping("/{id}")
    public ServerResponseEntity<Phrase> updatePhrase(@PathVariable long id, @RequestBody @Validated Phrase phrase){
        return ServerResponseEntity.success(JpaCrudUtil.updateResource(phrase, id, phraseRepository));
    }
}
