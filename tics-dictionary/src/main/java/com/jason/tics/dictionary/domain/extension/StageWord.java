package com.jason.tics.dictionary.domain.extension;

import com.jason.tics.common.security.annotation.Sortable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
@Table
@Entity
@IdClass(StageWord.class)
public class StageWord implements Serializable {
    @Id
    private String stageName;
    @Id
    @Sortable
    private String word;

    public StageWord() {
    }

    public StageWord(String stageName, String word) {
        this.stageName = stageName;
        this.word = word;
    }
}
