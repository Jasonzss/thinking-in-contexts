package com.jason.tics.store.controller.app;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.store.domain.Category;
import com.jason.tics.store.domain.Item;
import com.jason.tics.store.repository.CategoryRepository;
import com.jason.tics.store.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jason
 */
@RestController("/store/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public ServerResponseEntity<List<Category>> listAllCategories(){
        return ServerResponseEntity.success(categoryRepository.findAll());
    }

    @GetMapping("/{name}")
    public ServerResponseEntity<List<Item>> listItemsByCategory(@PathVariable String name){
        return ServerResponseEntity.success(itemRepository.findAllByCategoryName(name));
    }
}
