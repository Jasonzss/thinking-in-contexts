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
 * 用户个人笔记
 * @author Jason
 */
@Getter
@Setter
@ToString
@Table
@Entity
@IdClass(Note.NoteId.class)
public class Note {
    @Id
    @IsWord
    private String word;
    @Id
    private Long uid;
    private String note;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    public Note() {
    }

    public Note(String word, Long uid, String note) {
        this.word = word;
        this.uid = uid;
        this.note = note;
    }

    @Data
    public static class NoteId implements Serializable {
        private String word;
        private Long uid;

        public NoteId() {
        }

        public NoteId(String word, Long uid) {
            this.word = word;
            this.uid = uid;
        }
    }
}
