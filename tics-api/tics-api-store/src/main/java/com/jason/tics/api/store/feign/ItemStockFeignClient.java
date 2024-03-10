package com.jason.tics.api.store.feign;

import com.jason.tics.api.store.pojo.bo.ItemStockLockBo;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Jason
 */
@Component
@FeignClient(value = "tics-store")
public interface ItemStockFeignClient {
    /**
     * 锁住库存
     */
    @PostMapping("/store/item/{itemId}/stock/lock")
    ServerResponseEntity<ItemStockLockBo> lockItems(@PathVariable long itemId, int lockNum, Long orderId);

    /**
     * 删除库存锁，并选择库存确认还是释放
     */
    @DeleteMapping("/store/item/{itemId}/stock/lock/{orderId}")
    ServerResponseEntity<Void> deleteStockLock(@PathVariable long orderId, boolean confirmed);
}
