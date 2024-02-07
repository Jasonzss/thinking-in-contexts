package com.jason.tics.dictionary.domain;

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
@IdClass(BookWord.BookWordId.class)
public class BookWord {
    @Id
    private String word;
    @Id
    private Long bookId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    @Data
    public static class BookWordId implements Serializable {
        private String word;
        private Long bookId;

        public BookWordId() {
        }

        public BookWordId(String word, Long bookId) {
            this.word = word;
            this.bookId = bookId;
        }
    }
}
