package com.jason.tics.exercise.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.math.Fraction;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * 题目的基本信息
 * @author Jason
 */
@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseQuestion {
    @Id
    @Length(max = 25)
    private String questionId;

    @Enumerated(EnumType.STRING)
    @Length(max = 30)
    private QuestionType questionType;
    /**
     * 题目解析
     */
    @Length(max = 100)
    private String analysis;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

    public abstract Fraction answer(String... userAnswer);
}
