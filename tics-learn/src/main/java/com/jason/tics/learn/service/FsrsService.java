package com.jason.tics.learn.service;

import com.jason.tics.api.learn.domain.WordLearningResult;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * @author Jason
 */
public interface FsrsService {
    SortedSet<WordLearningResult> listUserWordLearningResult(long uid);

    Set<WordLearningResult> addWordSchedule(long uid, List<String> word);

    WordLearningResult learnWordWithRating(long uid, String word, int rating);

    SortedSet<WordLearningResult> listDailyWords(long uid);
}
