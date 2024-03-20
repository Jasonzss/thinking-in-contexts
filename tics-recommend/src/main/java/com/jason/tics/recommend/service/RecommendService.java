package com.jason.tics.recommend.service;

import java.util.Set;

/**
 * @author Jason
 */
public interface RecommendService {
    Set<String> recommend(long uid, int howMany);
}
