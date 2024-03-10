package com.jason.tics.store.feign;

import cn.hutool.core.bean.BeanUtil;
import com.jason.tics.api.store.feign.ItemStockFeignClient;
import com.jason.tics.api.store.pojo.bo.ItemStockBo;
import com.jason.tics.api.store.pojo.bo.ItemStockLockBo;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.store.repository.ItemStockRepository;
import com.jason.tics.store.service.ItemStockLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class ItemStockFeignController implements ItemStockFeignClient {
    @Autowired
    private ItemStockRepository itemStockRepository;
    @Autowired
    private ItemStockLockService itemStockLockService;

    @Override
    public ServerResponseEntity<ItemStockBo> getItemStock(long itemId) {
        ItemStockBo itemStockBo = BeanUtil.copyProperties(itemStockRepository.getByItemId(itemId), ItemStockBo.class);
        return ServerResponseEntity.success(itemStockBo);
    }

    @Override
    public ServerResponseEntity<ItemStockLockBo> lockItems(long itemId, int lockNum, Long orderId) {
        ItemStockLockBo bo = BeanUtil
                .copyProperties(itemStockLockService.lockStock(itemId, lockNum, orderId), ItemStockLockBo.class);
        return ServerResponseEntity.success(bo);
    }

    @Override
    public ServerResponseEntity<Void> deleteStockLock(long orderId, boolean confirmed) {
        if(confirmed){
            itemStockLockService.confirmStockLock(orderId);
        }else {
            itemStockLockService.releaseStockLock(orderId);
        }
        return ServerResponseEntity.success();
    }
}
