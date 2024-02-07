package com.jason.tics.exercise.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

/**
 * 试卷，题目集合
 * @author Jason
 */
@Setter
@Getter
@ToString
@Entity
@Table
public class Paper {
    @Id
    private Long id;
    @OneToMany(targetEntity = PaperQuestion.class, cascade = CascadeType.ALL, mappedBy = "paper")
    private List<PaperQuestion> questions;
    @Length(max = 30)
    private String paperName;
    private Long timeLimit;

    public Double getTotalScore(){
        double s = 0;
        for (PaperQuestion question : questions) {
            s += question.getScore();
        }
        return s;
    }
}
