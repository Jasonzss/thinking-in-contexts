package com.jason.tics.store.domain.pojo.dto;

import com.jason.tics.store.domain.Category;

/**
 * @author Jason
 */
public class CategoryDto {
    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String desc;

    /**
     * 分类图标url
     */
    private String iconUrl;

    public Category getCategory(){
        Category category = new Category();
        category.setDesc(desc);
        category.setIconUrl(iconUrl);
        category.setName(name);
        return category;
    }
}
