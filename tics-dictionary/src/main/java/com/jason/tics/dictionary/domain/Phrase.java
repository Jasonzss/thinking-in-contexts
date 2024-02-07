package com.jason.tics.dictionary.domain;

import com.jason.tics.common.jpa.converter.ListConverter;
import com.jason.tics.common.security.annotation.Sortable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
@Table
@Entity
public class Phrase {
    @Id
    private Long id;
    private String content;
    @Convert(converter = ListConverter.class)
    private List<String> translation;
    @Sortable
    private Integer likeNum;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Sortable
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Sortable
    private Date updateTime;
}
