package com.jason.tics.store.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * 商品
 * @author Jason
 */
@Data
@Entity
@Table
@Builder
@NoArgsConstructor
public class Item {
    @Id
    private Long itemId;

    @OneToMany
    private List<ItemImage> itemImagesUrl;

    private String itemName;

    /**
     * 售价
     */
    @Nullable
    private Long cost;

    /**
     * 积分售价
     * 两种售价为或的关系，只需取其中之一支付即可。属性为null代表无法以此方式支付
     */
    @Nullable
    private Long pointCost;

    /**
     * 商品详情
     */
    @Length(max = 2000)
    private String details;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
