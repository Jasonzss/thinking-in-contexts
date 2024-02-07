package com.jason.tics.dictionary.service;

import com.jason.tics.dictionary.domain.extension.StageWord;

import java.util.List;

/**
 * @author Jason
 */
public interface StageService {
    List<StageWord> addStageWord(List<String> stageNames, String word);
}
