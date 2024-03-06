package com.jason.tics.store.domain.pojo.dto;

import com.jason.tics.common.jpa.entity.Dto;
import com.jason.tics.store.domain.ItemStockLock;

/**
 * @author Jason
 */
public class ItemStockLockDto implements Dto<Long, ItemStockLock> {
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

    @Override
    public ItemStockLock getSource() {
        return ItemStockLock.builder()
                .itemId(itemId)
                .orderId(orderId)
                .count(count)
                .build();
    }

    @Override
    public Long getId() {
        return itemStockLockId;
    }
}
