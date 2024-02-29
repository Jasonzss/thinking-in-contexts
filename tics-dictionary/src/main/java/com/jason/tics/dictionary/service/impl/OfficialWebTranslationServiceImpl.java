package com.jason.tics.dictionary.service.impl;

import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.dictionary.domain.web.WebDictionary;
import com.jason.tics.dictionary.domain.web.WebTranslation;
import com.jason.tics.dictionary.repository.WebDictionaryRepository;
import com.jason.tics.dictionary.repository.WebTranslationRepository;
import com.jason.tics.dictionary.service.WebDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class OfficialWebTranslationServiceImpl implements WebDictionaryService {
    private static final WebDictionary OFFICIAL_WEB_DICTIONARY;

    public static final String OFFICIAL_THEME = "思文官方网络字典";
    public static final Long OFFICIAL_CREATOR_ID = 1L;

    @Autowired
    private WebDictionaryRepository webDictionaryRepository;
    @Autowired
    private WebTranslationRepository webTranslationRepository;

    static {
        OFFICIAL_WEB_DICTIONARY = new WebDictionary();
        OFFICIAL_WEB_DICTIONARY.setTheme(OFFICIAL_THEME);
        OFFICIAL_WEB_DICTIONARY.setCreatorId(OFFICIAL_CREATOR_ID);
    }


    @Override
    public WebTranslation addWebTranslation(WebTranslation webTranslation) {
        initOfficialWebDictionary();
        webTranslation.setTheme(OFFICIAL_THEME);
        if (webTranslation.getContributorId().equals(OFFICIAL_CREATOR_ID)) {
            throw new TicsException(ExceptionResponseEnum.NO_PERMISSION);
        }
        return webTranslationRepository.save(webTranslation);
    }

    private void initOfficialWebDictionary(){
        Example<WebDictionary> example = Example.of(OFFICIAL_WEB_DICTIONARY);
        if (!webDictionaryRepository.exists(example)) {
            webDictionaryRepository.save(OFFICIAL_WEB_DICTIONARY);
        }
    }
}
