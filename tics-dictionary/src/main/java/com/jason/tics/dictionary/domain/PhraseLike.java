package com.jason.tics.dictionary.domain;

import com.jason.tics.common.jpa.converter.BooleanConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
@Table
@Entity
@IdClass(PhraseLike.PhraseLikeId.class)
public class PhraseLike {
    @Id
    private Long uid;
    @Id
    private Long exampleId;
    @Convert(converter = BooleanConverter.class)
    private Boolean isLike;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    @Data
    public static class PhraseLikeId implements Serializable {
        private Long uid;
        private Long exampleId;
    }
}
