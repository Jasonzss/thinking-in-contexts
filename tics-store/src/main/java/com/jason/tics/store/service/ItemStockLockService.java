package com.jason.tics.store.service;

/**
 * @author Jason
 */
public interface ItemStockLockService {
    /**
     * 锁定商品的库存
     * @param itemId 商品id
     * @param stockNum 锁定数量
     */
    void lockStock(Long itemId, int stockNum);
}
