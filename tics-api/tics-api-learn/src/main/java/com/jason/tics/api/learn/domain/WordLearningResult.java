package com.jason.tics.api.learn.domain;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @author Jason
 */
@Data
public class WordLearningResult implements Comparable<WordLearningResult> {
    private String cardUuid;
    private String word;
    private WordStage stage;
    private Date nextRepetition;

    public WordLearningResult() {
    }

    public WordLearningResult(String cardUuid, String word, WordStage stage, Date nextRepetition) {
        this.cardUuid = cardUuid;
        this.word = word;
        this.stage = stage;
        this.nextRepetition = nextRepetition;
    }

    @Override
    public int compareTo(WordLearningResult o) {
        return nextRepetition.getTime() > o.nextRepetition.getTime() ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordLearningResult that = (WordLearningResult) o;
        return cardUuid.equals(that.cardUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardUuid);
    }
}
