package com.jason.tics.store.service;

import com.jason.tics.store.domain.ItemStockLock;

/**
 * @author Jason
 */
public interface ItemStockLockService {
    /**
     * 锁定商品的库存
     */
    ItemStockLock lockStock(long itemId, int lockNum, Long orderId);

    void releaseStockLock(Long orderId);

    void confirmStockLock(Long orderId);
}
