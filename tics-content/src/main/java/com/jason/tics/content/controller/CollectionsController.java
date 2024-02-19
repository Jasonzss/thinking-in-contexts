package com.jason.tics.content.controller;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.content.domain.Collection;
import com.jason.tics.content.domain.Collections;
import com.jason.tics.content.service.CollectionService;
import com.jason.tics.content.service.CollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jason
 */
@RequestMapping("collections")
@Controller
public class CollectionsController {
    @Autowired
    private CollectionsService collectionsService;
    @Autowired
    private CollectionService collectionService;

    @GetMapping("/{id}")
    public Collections getCollections(@PathVariable long id){
        return collectionsService.getById(id);
    }

    @PostMapping
    public ServerResponseEntity<Void> saveCollections(@Uid long uid, @RequestParam String name){
        return ServerResponseEntity.successOrShowFail(collectionsService.addCollections(name, uid),
                "创建收藏夹失败");
    }

    @DeleteMapping("/{id}")
    public ServerResponseEntity<Void> deleteCollections(@PathVariable long id){
        return ServerResponseEntity.successOrShowFail(collectionsService.removeById(id),
                "删除收藏夹失败");
    }

    @PutMapping("/{id}")
    public ServerResponseEntity<Void> udpateCollections(@RequestBody @Validated Collections collections){
        return ServerResponseEntity.successOrShowFail(collectionsService.updateCollections(collections),
                "修改收藏夹失败");
    }

    @GetMapping("/{id}/collection")
    public ServerResponseEntity<List<Collection>> getCollection(@RequestParam long uid,@RequestParam String contentId) {
        return ServerResponseEntity.success(collectionService.listCollectionByUidAndContentId(uid, contentId));
    }

    @PostMapping("/{id}/collection")
    public ServerResponseEntity<Void> addCollection(@Uid long uid,@RequestParam String contentId,
                                                    @RequestParam Long collectionId) {
        Collection collection = new Collection(uid, contentId, collectionId);
        return ServerResponseEntity.successOrShowFail(collectionService.save(collection), "收藏失败");
    }

    @DeleteMapping("/{id}/collection")
    public ServerResponseEntity<Void> deleteCollection(@Uid long uid,@RequestParam String contentId,
                                                       @RequestParam Long collectionId) {
        Collection collection = new Collection(uid, contentId, collectionId);
        collectionService.deleteCollection(uid, contentId, collectionId);
        return ServerResponseEntity.success();
    }
}
