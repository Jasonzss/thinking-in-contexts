package com.jason.tics.exercise.domain.exam.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
public class PaperDto {
    private Long id;
    private List<PaperQuestionDto> questions;
    private String paperName;
    private Long timeLimit;

    @Getter
    @Setter
    @ToString
    public static class PaperQuestionDto{
        private Double score;
        /**
         * 此题目在整张试卷的序号
         */
        private int index;
        private Long questionId;
    }
}
