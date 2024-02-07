package com.jason.tics.learn.service;

import com.jason.tics.api.learn.domain.WordLearningResult;

import java.util.List;

/**
 * @author Jason
 */
public interface FsrsService {
    List<WordLearningResult> listWordsStage(long uid);

    List<WordLearningResult> addWordSchedule(long uid, List<String> word);

    WordLearningResult learnWordWithRating(long uid, String word, int rating);
}
