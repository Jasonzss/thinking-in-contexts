package com.jason.tics.learn.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * 学习卡片，一个卡片对于应一个单词记忆
 * @author Jason
 */
@Data
@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Cards {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(columnDefinition = "card_uuid", length = 32)
    private String cardUuid;
    /**
     * 学习卡的正面，放置target word
     */
    private String front;
    /**
     * 禁用学习卡的背面，后面完善后再删
     */
    private String back;
    /**
     * 创建日期的时间戳
     */
    @CreationTimestamp
    private Date createTime;
    /**
     * 用户id
     */
    @NotNull
    private Long uid;

    public Cards() {
    }

    public Cards(String front, Long uid) {
        this.front = front;
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards = (Cards) o;
        return front.equals(cards.front) && uid.equals(cards.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(front, uid);
    }
}
