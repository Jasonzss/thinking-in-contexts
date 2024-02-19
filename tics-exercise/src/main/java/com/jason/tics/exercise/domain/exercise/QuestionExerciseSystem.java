package com.jason.tics.exercise.domain.exercise;

import com.jason.tics.exercise.domain.BaseQuestion;

/**
 * 题目练习
 * TODO
 * @author Jason
 */
public interface QuestionExerciseSystem {
    BaseQuestion startExercise(long uid);

    BaseQuestion answer(String... userAnswer);

    void endExercise();
}
