package com.jason.tics.dictionary.domain.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
@Entity
@Table
public class WebDictionary {
    @Id
    private String theme;
    @ElementCollection
    private List<Long> contributorIds;
    private String bannerImageUrl;
    private String themeLogoUrl;
    private String desc;
    @Column(updatable = false, nullable = false)
    private Long creatorId;


    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;
}
