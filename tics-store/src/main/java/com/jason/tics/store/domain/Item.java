package com.jason.tics.store.domain;

import com.jason.tics.api.store.domain.PayType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.math.BigDecimal;
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

    @Enumerated
    private PayType payType;

    @Nullable
    private BigDecimal cost;

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
