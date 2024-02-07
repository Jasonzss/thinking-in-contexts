package com.jason.tics.dictionary.service;

import com.jason.tics.dictionary.domain.vo.DictionaryVo;

/**
 * @author Jason
 */
public interface DictionaryService {
    DictionaryVo getDictionary(String query, long uid);

    boolean isWord(String query);
}
