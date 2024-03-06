package com.jason.tics.store.controller.admin;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.store.domain.ItemStock;
import com.jason.tics.store.domain.ItemStockLock;
import com.jason.tics.store.domain.pojo.dto.ItemStockDto;
import com.jason.tics.store.domain.pojo.dto.ItemStockLockDto;
import com.jason.tics.store.repository.ItemStockLockRepository;
import com.jason.tics.store.repository.ItemStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Jason
 */
@RestController("/admin/store/item/{itemId}/stock")
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

    @PutMapping
    public ServerResponseEntity<ItemStock> update(@PathVariable long itemId,
                                                  @Valid @RequestBody ItemStockDto itemStockDto){
        return ServerResponseEntity.success(itemStockRepository.updateByPojo(itemId, itemStockDto));
    }

    //item stock lock

    @GetMapping("/lock/{id}")
    public ServerResponseEntity<ItemStockLock> getStockLock(@PathVariable long id){
        return ServerResponseEntity.success(itemStockLockRepository.getById(id));
    }

    @DeleteMapping
    public ServerResponseEntity<ItemStockLock> deleteStockLock(@PathVariable long id){
        itemStockLockRepository.deleteById(id);
        return ServerResponseEntity.success();
    }

    @PostMapping
    public ServerResponseEntity<ItemStockLock> addStockLock(@Valid @RequestBody ItemStockLockDto itemStockLockDto){
        return ServerResponseEntity.success(itemStockLockRepository.addByPojo(itemStockLockDto));
    }
}
