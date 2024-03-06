package com.jason.tics.store.controller.app;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.store.domain.Item;
import com.jason.tics.store.domain.ItemImage;
import com.jason.tics.store.repository.ItemImageRepository;
import com.jason.tics.store.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jason
 */
@RestController("/store/item")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemImageRepository itemImageRepository;

    @GetMapping("{id}")
    public ServerResponseEntity<Item> getItem(@PathVariable long id){
        return ServerResponseEntity.success(itemRepository.getById(id));
    }

    @GetMapping("{id}/images")
    public ServerResponseEntity<List<ItemImage>> getItemImages(@PathVariable long id){
        return ServerResponseEntity.success(itemImageRepository.findAllByItemId(id));
    }

    @GetMapping("{id}/images/{index}")
    public ServerResponseEntity<ItemImage> getItemImage(@PathVariable long id, @PathVariable int index){
        return ServerResponseEntity.success(itemImageRepository.getById(new ItemImage.ItemImageId(id, index)));
    }
}
