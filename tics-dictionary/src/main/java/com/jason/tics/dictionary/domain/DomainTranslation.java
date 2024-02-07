package com.jason.tics.dictionary.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 领域翻译
 *
 * @author Jason
 */
@Getter
@Setter
@ToString
@Table
@Entity
public class DomainTranslation {
    @Id
    private Long id;
    private String target;
    private String translation;
    private String domainName;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    public DomainTranslation() {
    }

    public DomainTranslation(String target) {
        this.target = target;
    }
}
