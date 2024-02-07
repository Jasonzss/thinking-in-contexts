package com.jason.tics.translation.service;

import com.jason.tics.api.translation.bo.TranslationResult;

/**
 * @author Jason
 * @since 2023/09/10 - 18:15
 */
public interface ShortTextTranslateService {
    TranslationResult translate(String content);

    TranslationResult translateInContexts(String word, String sentence);
}
