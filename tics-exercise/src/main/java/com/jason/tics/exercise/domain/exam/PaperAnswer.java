package com.jason.tics.exercise.domain.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jason.tics.common.jpa.converter.StringArrayConverter;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Jason
 */
@Data
@Entity
@Table(name = "paper_answer")
public class PaperAnswer {
    @Id
    private Long id;

    /**
     * 指定答题卡的指定第几题
     */
    @ManyToOne
    @JoinColumn(name = "scantron_id")
    @ToString.Exclude
    @JsonIgnore
    private Scantron scantron;
    private Integer idx;

    @Convert(converter = StringArrayConverter.class)
    private String[] answer;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}