package com.jason.tics.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.tics.content.domain.Collection;

import java.util.List;

/**
 * @author Jason
 */
public interface CollectionService extends IService<Collection> {
    List<Collection> listCollectionByUidAndContentId(long uid, String contentId);

    void deleteCollection(long uid, String contentId, long collectionsId);
}
