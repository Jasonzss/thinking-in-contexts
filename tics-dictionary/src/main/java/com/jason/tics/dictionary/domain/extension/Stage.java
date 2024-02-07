package com.jason.tics.dictionary.domain.extension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 单词学习阶段
 *   中考、高考、CET4、CET6、考研、雅思、托福
 * @author Jason
 */
@Getter
@Setter
@ToString
@Table
@Entity
public class Stage {
    @Id
    private String stageName;
    private String desc;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    public Stage() {
    }

    public Stage(String stageName) {
        this.stageName = stageName;
    }
}
