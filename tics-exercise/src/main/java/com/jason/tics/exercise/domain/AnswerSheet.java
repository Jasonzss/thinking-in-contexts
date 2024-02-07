package com.jason.tics.exercise.domain;

import com.jason.tics.common.jpa.converter.BooleanConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 答题纸，在对应试卷的基础上留下的答案
 * @author Jason
 */
@Data
@Entity
@Table
public class AnswerSheet {
    /**
     * 答题卡id
     */
    @Id
    private Long id;
    private Long paperId;
    private Long uid;
    @Convert(converter = BooleanConverter.class)
    private Boolean isFinished = false;
    private Double score;
    private Long timeSpent;
    /**
     * 用户的回答，index即为题号，没写的题必须以空字符串占位
     */
    @OneToMany(fetch = FetchType.EAGER, targetEntity = PaperAnswer.class,
            cascade = CascadeType.ALL, mappedBy = "answerSheet")
    private List<PaperAnswer> userAnswers;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
