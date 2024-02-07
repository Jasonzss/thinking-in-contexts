package com.jason.tics.exercise.domain;

import lombok.Data;

import java.util.List;

/**
 * @author Jason
 */
@Data
public class WordTranslationChoiceExercise {
    private String word;
    private String speech;
    private List<String> choices;
}
