package com.jason.tics.exercise.event;

import com.jason.tics.exercise.domain.exercise.WordChoiceExerciseInfo;
import org.springframework.context.ApplicationEvent;

/**
 * @author Jason
 */
public class WordTranslationChoiceExerciseEndUpEvent extends ApplicationEvent {
    private WordChoiceExerciseInfo info;

    public WordTranslationChoiceExerciseEndUpEvent(Object source, WordChoiceExerciseInfo info) {
        super(source);
        this.info = info;
    }

    public WordChoiceExerciseInfo getInfo() {
        return info;
    }

    public void setInfo(WordChoiceExerciseInfo info) {
        this.info = info;
    }
}
