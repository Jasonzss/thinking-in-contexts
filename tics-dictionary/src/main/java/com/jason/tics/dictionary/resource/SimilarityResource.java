package com.jason.tics.dictionary.resource;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.dictionary.domain.extension.Similarity;
import com.jason.tics.dictionary.repository.SimilarityRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/dictionary/similarity")
public class SimilarityResource {
    private SimilarityRepository similarityRepository;

    @GetMapping("/{word}")
    public ServerResponseEntity<List<Similarity>> getSimilarity(String word){
        return ServerResponseEntity.success(similarityRepository.findAllByOneEqualsOrTwoEquals(word, word));
    }

    @PostMapping
    public ServerResponseEntity<Similarity> addSimilarity(@Validated @RequestBody
                                                                      Similarity similarity){
        return ServerResponseEntity.success(similarityRepository.save(similarity));
    }

    @DeleteMapping("/{word}/{similarity}")
    public ServerResponseEntity<Void> deleteSimilarity(@PathVariable String word, @PathVariable String similarity){
        similarityRepository.deleteById(new Similarity.SimilarityId(word, similarity));
        return ServerResponseEntity.success();
    }

    @PutMapping("/{word}/{similarity}")
    public ServerResponseEntity<Similarity> updateSimilarity(@PathVariable String word,
                                                             @PathVariable String similarity, String difference){
        return ServerResponseEntity.success(similarityRepository.save(new Similarity(word, similarity, difference)));
    }
}
