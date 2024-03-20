package com.jason.tics.recommend.controller;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.recommend.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/recommend")
public class ContentRecommendController {
    @Autowired
    private RecommendService recommendService;

    @GetMapping("/content")
    public ServerResponseEntity<Set<String>> getRecommendContent(
            @Uid long uid,
            @RequestParam(required = false, defaultValue = "10") int num) {
        return ServerResponseEntity.success(recommendService.recommend(uid, num));
    }
}
