package com.jason.tics.exercise.domain;

import java.util.List;

/**
 * @author Jason
 */
public interface QuestionExerciseSystem {
    List<Long> listQuestions(long uid);

    BaseQuestion answer(String... userAnswer);


}
