package com.jason.tics.exercise.domain;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jason
 */
@Component
public class SimpleWordExercise implements WordExerciseSystem{
    @Override
    public WordTranslationChoiceExercise nextWordExercise(Long uid) {
        return null;
    }

    @Override
    public boolean pointWord(Long uid, String word, String answer) {
        return false;
    }

    @Override
    public List<ExerciseResult> finishExercise(Long uid) {
        return null;
    }
}
