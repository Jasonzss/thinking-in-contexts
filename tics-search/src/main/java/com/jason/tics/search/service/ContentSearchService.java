package com.jason.tics.search.service;

import com.jason.tics.api.search.bo.SearchResult;

/**
 * @author Jason
 */
public interface ContentSearchService {
    SearchResult<?> searchAll(String search, int page, int size, String... types);
}
