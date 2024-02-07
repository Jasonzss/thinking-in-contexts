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
@IdClass(ExampleLike.ExampleLikeId.class)
public class ExampleLike {
    @Id
    private Long uid;
    @Id
    private Long exampleId;
    //null代表啥也没有，负数代表不喜欢，正数代表喜欢
    @Convert(converter = BooleanConverter.class)
    private Boolean isLike;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    public ExampleLike() {
    }

    public ExampleLike(Long uid, Long exampleId, Boolean isLike) {
        this.uid = uid;
        this.exampleId = exampleId;
        this.isLike = isLike;
    }

    @Data
    public static class ExampleLikeId implements Serializable {
        private Long uid;
        private Long exampleId;

        public ExampleLikeId() {
        }

        public ExampleLikeId(Long uid, Long exampleId) {
            this.uid = uid;
            this.exampleId = exampleId;
        }
    }
}
