package com.jason.tics.api.store.feign;

import com.jason.tics.api.store.pojo.bo.ItemBo;
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
public interface ItemFeignClient {
    @GetMapping("/store/item/{itemId}")
    ServerResponseEntity<ItemBo> getItem(@PathVariable long itemId);
}
