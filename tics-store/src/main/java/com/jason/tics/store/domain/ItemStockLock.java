package com.jason.tics.store.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 与订单一对一，代表某订单锁定的库存数量
 * @author Jason
 */
@Data
@Entity
@Table
@Builder
public class ItemStockLock {
    /**
     * 库存锁id
     */
    @Id
    private Long itemStockLockId;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 锁定库存数量
     */
    private Integer count;


    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

    public ItemStockLock() {
    }
}
