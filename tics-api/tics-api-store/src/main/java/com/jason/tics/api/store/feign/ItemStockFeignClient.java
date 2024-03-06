package com.jason.tics.api.store.feign;

import com.jason.tics.api.store.pojo.bo.ItemStockBo;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jason
 */
@Component
@FeignClient(value = "tics-store")
public interface ItemStockFeignClient {
    /**
     * 查询库存
     */
    @GetMapping("/store/item/{itemId}/stock")
    ServerResponseEntity<ItemStockBo> getItemStock(@PathVariable long itemId);


}
