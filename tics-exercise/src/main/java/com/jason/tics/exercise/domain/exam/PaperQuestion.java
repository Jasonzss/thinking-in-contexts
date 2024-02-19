package com.jason.tics.exercise.domain.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 题目之于试卷上的基础信息
 * @author Jason
 */
@Data
@Entity
@Table(name = "paper_question")
public class PaperQuestion {
    @Id
    private Long id;

    /**
     * 此题目的分值
     */
    private Double score;
    /**
     * 此题目在整张试卷的序号
     */
    private int index;

    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "paper_id")
    @ToString.Exclude
    @JsonIgnore
    private Paper paper;
}
