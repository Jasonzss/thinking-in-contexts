package com.jason.tics.store.service.impl;

import com.jason.tics.store.domain.ItemStock;
import com.jason.tics.store.domain.ItemStockLock;
import com.jason.tics.store.repository.ItemStockLockRepository;
import com.jason.tics.store.repository.ItemStockRepository;
import com.jason.tics.store.service.ItemStockLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class ItemStockLockServiceImpl implements ItemStockLockService {
    @Autowired
    private ItemStockLockRepository itemStockLockRepository;
    @Autowired
    private ItemStockRepository itemStockRepository;

    @Override
    public ItemStockLock lockStock(long itemId, int lockNum, Long orderId) {
        ItemStock stock = itemStockRepository.getByItemId(itemId);
        if (stock.getAvailableStock() >= lockNum) {
            stock.lockStock(lockNum);
        }
        ItemStockLock lock = new ItemStockLock();
        lock.setItemId(itemId);
        lock.setCount(lockNum);
        lock.setOrderId(orderId);
        return itemStockLockRepository.save(lock);
    }

    @Override
    public void releaseStockLock(Long orderId) {
        ItemStockLock lock = itemStockLockRepository.getByOrderId(orderId);
        itemStockLockRepository.deleteByOrderId(orderId);
        ItemStock stock = itemStockRepository.getByItemId(lock.getItemId());
        stock.releaseLock(lock.getCount());
        itemStockRepository.save(stock);
    }

    @Override
    public void confirmStockLock(Long orderId) {
        ItemStockLock lock = itemStockLockRepository.getByOrderId(orderId);
        itemStockLockRepository.deleteByOrderId(orderId);
        ItemStock stock = itemStockRepository.getByItemId(lock.getItemId());
        stock.confirmStockLock(lock.getCount());
        itemStockRepository.save(stock);
    }
}
