package com.jason.tics.dictionary.domain.web;

import com.jason.tics.common.jpa.validator.CrudGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull(groups = CrudGroup.Insert.class)
    private Long creatorId;


    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;
}
