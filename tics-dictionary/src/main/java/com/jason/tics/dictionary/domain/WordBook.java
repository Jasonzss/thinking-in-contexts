package com.jason.tics.dictionary.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
@Table
@Entity
public class WordBook {
    @Id
    private Long bookId;
    private String title;
    private Long creatorId;
    private String coverImageUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;
}
