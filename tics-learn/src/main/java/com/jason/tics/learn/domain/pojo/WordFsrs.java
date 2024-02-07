package com.jason.tics.learn.domain.pojo;

import com.jason.tics.learn.domain.Fsrs;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Jason
 */
@Data
@Entity
@Table(name = "cards")
public class WordFsrs {
    @Id
    private String cardUuid;
    @Column(name = "front")
    private String front;
    private long uid;
    @OneToOne(targetEntity = Fsrs.class)
    @JoinColumn(name = "card_uuid")
    private Fsrs fsrs;
}
