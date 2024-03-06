package com.jason.tics.store.controller.app;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.store.domain.ItemStock;
import com.jason.tics.store.repository.ItemStockLockRepository;
import com.jason.tics.store.repository.ItemStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController("/store/item/{itemId}/stock")
public class ItemStockController {
    @Autowired
    private ItemStockRepository itemStockRepository;
    @Autowired
    private ItemStockLockRepository itemStockLockRepository;

    //item stock

    @GetMapping
    public ServerResponseEntity<ItemStock> get(@PathVariable long itemId){
        return ServerResponseEntity.success(itemStockRepository.getByItemId(itemId));
    }
}
