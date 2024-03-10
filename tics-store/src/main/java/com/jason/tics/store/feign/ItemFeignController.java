package com.jason.tics.store.feign;

import cn.hutool.core.bean.BeanUtil;
import com.jason.tics.api.store.feign.ItemFeignClient;
import com.jason.tics.api.store.pojo.bo.ItemBo;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.store.domain.Item;
import com.jason.tics.store.domain.ItemImage;
import com.jason.tics.store.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason
 */
@Component
public class ItemFeignController implements ItemFeignClient {
    @Autowired
    private ItemRepository itemRepository;

    public ServerResponseEntity<ItemBo> getItem(long itemId){
        Item item = itemRepository.getById(itemId);
        ItemBo itemBo = BeanUtil.copyProperties(item, ItemBo.class, "itemImagesUrl");
        Map<Integer, String> images = new HashMap<>();
        for (ItemImage image : item.getItemImagesUrl()) {
            images.put(image.getItemImageId().getImageIndex(), image.getUrl());
        }
        itemBo.setItemImagesUrl(images);
        return ServerResponseEntity.success(itemBo);
    }
}
