package com.jason.tics.store.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jason
 */

@Data
@Entity
@Table
public class CategoryItem {
    @Id
    private Long categoryId;
    @Id
    private Long itemId;

    public CategoryItem() {
    }

    public CategoryItem(Long categoryId, Long itemId) {
        this.categoryId = categoryId;
        this.itemId = itemId;
    }
}
