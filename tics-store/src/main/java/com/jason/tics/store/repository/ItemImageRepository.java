package com.jason.tics.store.repository;

import com.jason.tics.common.jpa.repository.TicsRepository;
import com.jason.tics.store.domain.ItemImage;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Jason
 */
public interface ItemImageRepository extends TicsRepository<ItemImage, ItemImage.ItemImageId> {
    @Query(value = "select * from item_image where item_id = ?1",nativeQuery = true)
    List<ItemImage> findAllByItemId(long itemId);
}
