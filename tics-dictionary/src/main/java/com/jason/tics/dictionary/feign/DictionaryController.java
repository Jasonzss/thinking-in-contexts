package com.jason.tics.dictionary.feign;

import com.jason.tics.api.dictionary.bo.ConfusedDictionaryBo;
import com.jason.tics.api.dictionary.feign.DictionaryFeignClient;
import com.jason.tics.dictionary.domain.extension.Variant;
import com.jason.tics.dictionary.domain.vo.DictionaryVo;
import com.jason.tics.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * @author Jason
 */
public class DictionaryController implements DictionaryFeignClient {
    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public ConfusedDictionaryBo getConfusedDictionary(String word) {
        DictionaryVo dictionary = dictionaryService.getDictionary(word, 0);
        ConfusedDictionaryBo bo = new ConfusedDictionaryBo();
        bo.setTarget(word);
        bo.setUsPhonetic(dictionary.getUsPhonetic());
        bo.setUkPhonetic(dictionary.getUkPhonetic());
        bo.setUsSpeech(dictionary.getUsSpeech());
        bo.setUkSpeech(dictionary.getUkSpeech());
        bo.setTranslation(dictionary.getTranslation());

        bo.setSynonyms(dictionary.getSimilarity().stream()
                .map(similarity -> similarity.getSimilarity(word))
                .collect(Collectors.toList()));

        bo.setSynonyms(dictionary.getSynonyms().stream()
                .map(synonym -> synonym.getSynonym(word))
                .collect(Collectors.toList()));

        bo.setVariants(dictionary.getVariants().stream()
                .map(Variant::getTarget)
                .collect(Collectors.toList()));

        return bo;
    }
}
