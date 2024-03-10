package com.jason.tics.common.cache.service;

import java.util.List;

/**
 * @author Jason
 */
public interface IdService {
    Long getIncreaseId(String key);

    Long getIncreaseId(String key, int step);

    List<Long> getMultiIncreaseId(String key, int num);
}
