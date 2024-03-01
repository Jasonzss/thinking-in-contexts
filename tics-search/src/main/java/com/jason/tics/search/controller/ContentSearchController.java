package com.jason.tics.search.controller;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.search.service.ContentSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController("content/search")
public class ContentSearchController {
    @Autowired
    private ContentSearchService contentSearchService;

    @GetMapping
    public ServerResponseEntity<?> searchContent(@RequestParam String search, @RequestParam int page,
                                                 @RequestParam int size,@RequestParam String[] types){
        return ServerResponseEntity.success(contentSearchService.searchAll(search, page, size, types));
    }
}
