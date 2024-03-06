package com.jason.tics.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 分类信息
 * @author Jason
 */
@Data
@Entity
@Table
public class Category {
    @Id
    private Long categoryId;
    /**
     * 分类名称
     */
    @NotNull
    private String name;

    /**
     * 分类描述
     */
    private String desc;

    /**
     * 分类图标url
     */
    private String iconUrl;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

    @OneToOne(mappedBy = "category", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private CategoryItem categoryItem;
}
