package com.jason.tics.store.repository;

import com.jason.tics.common.jpa.repository.TicsRepository;
import com.jason.tics.store.domain.ItemStock;

/**
 * @author Jason
 */
public interface ItemStockRepository extends TicsRepository<ItemStock, Long> {
    ItemStock getByItemId(long itemId);
}
