package com.jason.tics.api.learn.domain;

import lombok.Data;

/**
 * @author Jason
 */
@Data
public class WordLearningResult {
    private String cardUuid;
    private String word;
    private WordStage stage;

    public WordLearningResult() {
    }

    public WordLearningResult(String cardUuid, String word, WordStage stage) {
        this.cardUuid = cardUuid;
        this.word = word;
        this.stage = stage;
    }
}
