package com.jason.tics.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.tics.content.domain.Collection;
import com.jason.tics.content.mapper.CollectionMapper;
import com.jason.tics.content.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public List<Collection> listCollectionByUidAndContentId(long uid, String contentId) {
        LambdaQueryWrapper<Collection> wrapper = new QueryWrapper<Collection>().lambda();
        wrapper.eq(Collection::getUid, uid).eq(Collection::getContentId, contentId);
        return collectionMapper.selectList(wrapper);
    }

    @Override
    public void deleteCollection(long uid, String contentId, long collectionsId) {
        LambdaQueryWrapper<Collection> wrapper = new QueryWrapper<Collection>().lambda();
        wrapper.eq(Collection::getUid, uid).eq(Collection::getContentId, contentId)
                .eq(Collection::getCollectionsId, collectionsId);
        collectionMapper.delete(wrapper);
    }
}
