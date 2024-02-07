package com.jason.tics.exercise.domain;

import java.util.List;

/**
 * @author Jason
 */
public interface WordExerciseSystem {
    WordTranslationChoiceExercise nextWordExercise(Long uid);

    boolean pointWord(Long uid, String word, String answer);

    List<ExerciseResult> finishExercise(Long uid);
}
