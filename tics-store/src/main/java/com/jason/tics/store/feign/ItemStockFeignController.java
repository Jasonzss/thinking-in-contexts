package com.jason.tics.store.feign;

import cn.hutool.core.bean.BeanUtil;
import com.jason.tics.api.store.feign.ItemStockFeignClient;
import com.jason.tics.api.store.pojo.bo.ItemStockBo;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.store.repository.ItemStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class ItemStockFeignController implements ItemStockFeignClient {
    @Autowired
    private ItemStockRepository itemStockRepository;

    @Override
    public ServerResponseEntity<ItemStockBo> getItemStock(long itemId) {
        ItemStockBo itemStockBo = BeanUtil.copyProperties(itemStockRepository.getByItemId(itemId), ItemStockBo.class);
        return ServerResponseEntity.success(itemStockBo);
    }
}
