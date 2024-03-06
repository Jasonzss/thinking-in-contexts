package com.jason.tics.store.repository;

import com.jason.tics.common.jpa.repository.TicsRepository;
import com.jason.tics.store.domain.Item;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Jason
 */
public interface ItemRepository extends TicsRepository<Item, Long> {
    @Query(value = "select * from item where item_id in " +
            "(select * from category_item where category_id = " +
            "(select category_id from category where name = ?1))", nativeQuery = true)
    List<Item> findAllByCategoryName(String categoryName);
}
