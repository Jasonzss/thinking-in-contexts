package com.jason.tics.store.domain.pojo.dto;

import com.jason.tics.common.jpa.entity.Dto;
import com.jason.tics.store.domain.ItemStock;

/**
 * @author Jason
 */
public class ItemStockDto implements Dto<Long, ItemStock> {
    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 锁定库存
     */
    private Integer lockStock;

    /**
     * 可售卖库存
     */
    private Integer stock;

    @Override
    public ItemStock getSource() {
        return ItemStock.builder()
                .lockStock(lockStock)
                .availableStock(stock)
                .build();
    }
}
