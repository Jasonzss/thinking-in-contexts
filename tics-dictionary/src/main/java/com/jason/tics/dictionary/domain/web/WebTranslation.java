package com.jason.tics.dictionary.domain.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
public class WebTranslation {
    /**
     * 可以是 单词、缩写、一个短语、一个长句（长度有限制）
     */
    @Id
    private Long id;
    private String target;
    private String theme;
    private Long contributorId;
    private String translation;


    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    public WebTranslation() {
    }

    public WebTranslation(String target, String theme, Long contributorId, String translation) {
        this.target = target;
        this.theme = theme;
        this.contributorId = contributorId;
        this.translation = translation;
    }
}
