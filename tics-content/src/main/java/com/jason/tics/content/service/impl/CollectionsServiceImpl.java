package com.jason.tics.content.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.tics.content.domain.Collections;
import com.jason.tics.content.mapper.CollectionsMapper;
import com.jason.tics.content.service.CollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class CollectionsServiceImpl extends ServiceImpl<CollectionsMapper, Collections> implements CollectionsService {
    @Autowired
    private CollectionsMapper collectionsMapper;

    @Override
    public boolean addCollections(String name, long uid) {
        Collections collections = new Collections();
        collections.setName(name);
        collections.setUid(uid);
        DateTime date = DateUtil.date();
        collections.setCreateTime(date);
        collections.setUpdateTime(date);
        return collectionsMapper.insert(collections) > 0;
    }

    @Override
    public boolean updateCollections(Collections collections) {
        return collectionsMapper.updateById(collections) > 0;
    }
}
