package com.jason.tics.dictionary.domain.extension;

import com.jason.tics.dictionary.constraints.IsWord;
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
 * 近义词
 * rob - steal - deprive - plunder
 * @author Jason
 */
@Getter
@Setter
@ToString
@Table
@Entity
@IdClass(Synonym.SynonymsId.class)
public class Synonym {
    @Id
    @IsWord
    private String one;
    @Id
    @IsWord
    private String two;
    private String difference;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    public Synonym() {
    }

    public Synonym(String one, String two, String difference) {
        this.one = one;
        this.two = two;
        this.difference = difference;
    }

    @Data
    public static class SynonymsId implements Serializable {
        private String one;
        private String two;
    }

    public String getSynonym(String word){
        return word.equals(one) ? two : one;
    }
}
