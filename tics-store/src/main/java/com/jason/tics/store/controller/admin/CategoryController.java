package com.jason.tics.store.controller.admin;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.store.domain.Category;
import com.jason.tics.store.domain.CategoryItem;
import com.jason.tics.store.domain.Item;
import com.jason.tics.store.domain.pojo.dto.CategoryDto;
import com.jason.tics.store.repository.CategoryItemRepository;
import com.jason.tics.store.repository.CategoryRepository;
import com.jason.tics.store.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jason
 */
@RestController("/admin/store/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryItemRepository categoryItemRepository;

    @GetMapping
    public ServerResponseEntity<List<Category>> listAllCategories(){
        return ServerResponseEntity.success(categoryRepository.findAll());
    }

    @GetMapping("/{name}")
    public ServerResponseEntity<List<Item>> listItemsByCategory(@PathVariable String name){
        return ServerResponseEntity.success(itemRepository.findAllByCategoryName(name));
    }

    @PostMapping
    public ServerResponseEntity<Category> addCategory(@Validated @RequestBody CategoryDto categoryDto){
        return ServerResponseEntity.success(categoryRepository.save(categoryDto.getCategory()));
    }

    @DeleteMapping("/{name}")
    public ServerResponseEntity<Void> deleteCategory(@PathVariable String name){
        categoryRepository.deleteByName(name);
        return ServerResponseEntity.success();
    }

    @PutMapping("/{id}")
    public ServerResponseEntity<Category> updateCategory(@Validated @RequestBody CategoryDto categoryDto,@PathVariable Long id){
        Category category = categoryDto.getCategory();
        category.setCategoryId(id);
        return ServerResponseEntity.success(categoryRepository.save(category));
    }

    //category Item

    @PostMapping
    public ServerResponseEntity<CategoryItem> addCategoryItem(@RequestBody @Validated CategoryItem categoryItem){
        return ServerResponseEntity.success(categoryItemRepository.save(categoryItem));
    }

    @DeleteMapping("/{id}")
    public ServerResponseEntity<Void> deleteCategoryItem(@RequestBody @Validated CategoryItem categoryItem){
        categoryItemRepository.delete(categoryItem);
        return ServerResponseEntity.success();
    }
}
