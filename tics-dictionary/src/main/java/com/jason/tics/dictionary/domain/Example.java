package com.jason.tics.dictionary.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 例句
 * @author Jason
 */
@Getter
@Setter
@ToString
@Table
@Entity
public class Example {
    @Id
    private Long id;
    private String sentence;
    private Long contributorId;
    private String translation;
    private String speakUrl;
    private Integer likeNum;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    public void likeNumUpdate(int v){
        likeNum += v;
    }
}
