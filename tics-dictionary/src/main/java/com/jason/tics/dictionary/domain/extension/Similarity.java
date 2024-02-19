package com.jason.tics.dictionary.domain.extension;

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
 * 形相似单词
 *
 * @author Jason
 */
@Getter
@Setter
@ToString
@Table
@Entity
@IdClass(Similarity.SimilarityId.class)
public class Similarity {
    @Id
    private String one;
    @Id
    private String two;
    private String difference;


    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    public Similarity() {
    }

    public Similarity(String one, String two, String difference) {
        this.one = one;
        this.two = two;
        this.difference = difference;
    }

    @Data
    public static class SimilarityId implements Serializable {
        private String one;
        private String two;

        public SimilarityId() {
        }

        public SimilarityId(String one, String two) {
            this.one = one;
            this.two = two;
        }
    }

    public String getSimilarity(String word){
        return word.equals(one) ? two : one;
    }
}
