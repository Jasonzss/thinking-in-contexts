package com.jason.tics.dictionary.service.impl;

import com.jason.tics.api.translation.bo.TranslationResult;
import com.jason.tics.api.translation.feign.TranslationFeignClient;
import com.jason.tics.dictionary.domain.BookWord;
import com.jason.tics.dictionary.domain.vo.DictionaryVo;
import com.jason.tics.dictionary.repository.*;
import com.jason.tics.dictionary.service.DictionaryService;
import com.jason.tics.dictionary.service.DomainTranslationService;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason
 */
@Service
@Setter
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private TranslationFeignClient translationFeignClient;
    @Autowired
    private DomainTranslationService domainTranslationService;
    @Autowired
    private WebTranslationRepository webTranslationRepository;
    @Autowired
    private WordBookRepository wordBookRepository;
    @Autowired
    private PhraseRepository phraseRepository;
    @Autowired
    private ExampleRepository exampleRepository;
    @Autowired
    private SimilarityRepository similarityRepository;
    @Autowired
    private SynonymRepository synonymRepository;
    @Autowired
    private VariantRepository variantRepository;
    @Autowired
    private EtymologyRepository etymologyRepository;
    @Autowired
    private BookWordRepository bookWordRepository;

    /**
     * TODO 这样的分开查询后再合并有点慢，后续可替换成mybatis
     */
    @Override
    public DictionaryVo getDictionary(String query, long uid) {
        TranslationResult result = translationFeignClient.translate(query, null).getData();
        DictionaryVo d = new DictionaryVo();
        d.setTarget(query);
        d.setUsPhonetic(result.getUsPhonetic());
        d.setUkPhonetic(result.getUkPhonetic());
        d.setUsSpeech(result.getUsSpeech());
        d.setUkSpeech(result.getUkSpeech());
        d.setStage(result.getStages());
        d.setTranslation(result.getTranslation());
        d.setDomainTranslations(domainTranslationService.searchDomainTranslation(query, uid));
        d.setWebTranslation(webTranslationRepository.getWebTranslationByTargetEquals(query));
        List<Long> wordBookIds = new ArrayList<>();
        for (BookWord bookWord : bookWordRepository.findAllByWord(query)) {
            wordBookIds.add(bookWord.getBookId());
        }
        d.setWordBook(wordBookRepository.findAllById(wordBookIds));
        d.setPhrase(phraseRepository.findByContentContainingOrderByLikeNum(query, Pageable.ofSize(5)));
        d.setExample(exampleRepository.findBySentenceContaining(query, Pageable.ofSize(5)));
        d.setSimilarity(similarityRepository.findAllByOneEqualsOrTwoEquals(query, query));
        d.setSynonyms(synonymRepository.findAllByOneEqualsOrTwoEquals(query, query));
        variantRepository.findById(query).ifPresent(variants -> d.setVariants(variantRepository.findAllByVariantsGroupId(variants.getVariantsGroupName())));
        d.setEtymology(etymologyRepository.getById(query));

        return d;
    }

    @Override
    public boolean isWord(String query) {
        return StringUtils.isNotEmpty(query) && (
                translationFeignClient.translate(query, null).getData().isWord()
                || !webTranslationRepository.getWebTranslationByTargetEquals(query).isEmpty()
                || !domainTranslationService.searchDomainTranslation(query, null).isEmpty()
        );
    }
}
