package com.jason.tics.exercise.domain;

import lombok.Data;
import org.apache.commons.lang3.math.Fraction;

/**
 * 记录练习结果，不持久化
 * @author Jason
 */
@Data
public class ExerciseResult {
    private String questionId;
    private String[] rightAnswers;
    private String[] userAnswers;
    private Fraction core;
}
