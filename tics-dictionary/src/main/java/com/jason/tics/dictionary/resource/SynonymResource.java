package com.jason.tics.dictionary.resource;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.dictionary.domain.extension.Synonym;
import com.jason.tics.dictionary.repository.SynonymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 同义词资源
 * @author Jason
 */
@RestController
@RequestMapping("/dictionary/synonym")
public class SynonymResource {
    @Autowired
    public SynonymRepository synonymRepository;

    @GetMapping("/{word}")
    public ServerResponseEntity<List<Synonym>> getSynonym(@PathVariable String word){
        return ServerResponseEntity.success(synonymRepository.findAllByOneEqualsOrTwoEquals(word, word));
    }

    @PostMapping
    public ServerResponseEntity<Synonym> getSynonym(@RequestBody @Validated Synonym synonym){
        return ServerResponseEntity.success(synonymRepository.save(synonym));
    }

    @DeleteMapping("/{word}/{synonym}")
    public ServerResponseEntity<Synonym> deleteSynonym(@RequestBody @Validated Synonym synonym){
        return ServerResponseEntity.success(synonymRepository.save(synonym));
    }

    @PutMapping("/{word}/{synonym}")
    public ServerResponseEntity<Synonym> updateSynonym(@PathVariable String word,
                                                       @PathVariable String synonym,
                                                       @RequestParam String difference){
        return ServerResponseEntity.success(new Synonym(word, synonym, difference));
    }
}
