package com.jason.tics.store.controller.admin;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.store.domain.Item;
import com.jason.tics.store.domain.ItemImage;
import com.jason.tics.store.domain.pojo.dto.ItemDto;
import com.jason.tics.store.repository.ItemImageRepository;
import com.jason.tics.store.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Jason
 */
@RestController("/admin/store/item")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemImageRepository itemImageRepository;

    //item

    @GetMapping("{id}")
    public ServerResponseEntity<Item> getItem(@PathVariable long id){
        return ServerResponseEntity.success(itemRepository.getById(id));
    }

    @PostMapping
    public ServerResponseEntity<Item> addItem(@RequestBody @Valid ItemDto itemDto){
        return ServerResponseEntity.success(itemRepository.addByPojo(itemDto));
    }

    @DeleteMapping("{id}")
    public ServerResponseEntity<Void> deleteItem(@PathVariable long id){
        itemRepository.deleteById(id);
        return ServerResponseEntity.success();
    }

    @PutMapping("{id}")
    public ServerResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody @Valid ItemDto itemDto){
        return ServerResponseEntity.success(itemRepository.updateByPojo(id, itemDto));
    }

    //ItemImage

    @GetMapping("{id}/images")
    public ServerResponseEntity<List<ItemImage>> getItemImages(@PathVariable long id){
        return ServerResponseEntity.success(itemImageRepository.findAllByItemId(id));
    }

    @GetMapping("{id}/images/{index}")
    public ServerResponseEntity<ItemImage> getItemImage(@PathVariable long id, @PathVariable int index){
        return ServerResponseEntity.success(itemImageRepository.getById(new ItemImage.ItemImageId(id, index)));
    }

    @DeleteMapping("{id}/images/{index}")
    public ServerResponseEntity<Void> deleteItemImages(@PathVariable long id, @PathVariable int index){
        itemImageRepository.deleteById(new ItemImage.ItemImageId(id, index));
        return ServerResponseEntity.success();
    }

    @PutMapping("{id}/images")
    public ServerResponseEntity<List<ItemImage>> updateItemImages(@PathVariable long id,
                                                                  @RequestBody @Valid List<ItemImage> images){
        return ServerResponseEntity.success(itemImageRepository.saveAll(images));
    }
}
