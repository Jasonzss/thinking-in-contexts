package com.jason.tics.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.tics.content.domain.Collections;

/**
 * @author Jason
 */
public interface CollectionsService extends IService<Collections> {
    boolean addCollections(String name, long uid);

    boolean updateCollections(Collections collections);
}
