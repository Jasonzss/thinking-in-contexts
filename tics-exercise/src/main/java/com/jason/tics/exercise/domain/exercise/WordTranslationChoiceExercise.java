package com.jason.tics.exercise.domain.exercise;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Jason
 */
@Data
public class WordTranslationChoiceExercise {
    private String word;
    private String speech;
    private List<String> choices;
    private String rightAnswer;

    public boolean point(String userAnswer){
        return rightAnswer.equals(userAnswer);
    }

    public void addChoice(String word){
        if (choices == null) {
            choices = new ArrayList<>();
        }
        choices.add(word);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordTranslationChoiceExercise exercise = (WordTranslationChoiceExercise) o;
        return Objects.equals(word, exercise.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}
