package com.jason.tics.dictionary.resource;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.security.annotation.SortableEntity;
import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.dictionary.domain.Example;
import com.jason.tics.dictionary.domain.ExampleLike;
import com.jason.tics.dictionary.repository.ExampleLikeRepository;
import com.jason.tics.dictionary.repository.ExampleRepository;
import com.jason.tics.dictionary.service.ExampleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author Jason
 */
@RestController
@RequestMapping("/dictionary/example")
public class ExampleResource {
    @Autowired
    private ExampleRepository exampleRepository;
    @Autowired
    private ExampleLikeRepository exampleLikeRepository;
    @Autowired
    private ExampleLikeService exampleLikeService;

    @GetMapping
    public ServerResponseEntity<Page<Example>> searchExample(
            @RequestParam String query,
            @SortableEntity(entity = Example.class) Pageable pageable){
        return ServerResponseEntity.success(exampleRepository.findBySentenceContaining(query, pageable));
    }

    @GetMapping("/{id}")
    public ServerResponseEntity<Example> getExample(@PathVariable long id){
        return ServerResponseEntity.success(exampleRepository.getById(id));
    }

    @PostMapping
    public ServerResponseEntity<Example> addExample(@RequestBody @Validated Example example){
        return ServerResponseEntity.success(exampleRepository.save(example));
    }

    @DeleteMapping("/{id}")
    public ServerResponseEntity<Example> deleteExample(@PathVariable long id){
        exampleRepository.deleteById(id);
        return ServerResponseEntity.success();
    }

    @PutMapping("/{id}")
    public ServerResponseEntity<Example> updateExample(@PathVariable long id, @RequestBody @Validated Example example){
        example.setId(id);
        return ServerResponseEntity.success(exampleRepository.save(example));
    }

    //例句点赞相关

    @GetMapping("/{id}/likes/num")
    public ServerResponseEntity<Integer> getLikeNum(@PathVariable long id){
        return ServerResponseEntity.success(exampleLikeRepository
                .countByExampleIdAndIsLikeGreaterThanEqual(id, true));
    }


    @GetMapping("/{id}/unlikes/num")
    public ServerResponseEntity<Integer> getUnlikeNum(@PathVariable long id){
        return ServerResponseEntity.success(exampleLikeRepository
                .countByExampleIdAndIsLikeLessThanEqual(id, false));
    }

    @GetMapping("/{id}/like")
    public ServerResponseEntity<ExampleLike> isLiked(@Uid long uid, @PathVariable long id){
        return ServerResponseEntity.success(exampleLikeRepository
                .findById(new ExampleLike.ExampleLikeId(uid, id)).orElse(null));
    }

    @PostMapping("/{id}/like")
    @PutMapping("/{id}/like")
    public ServerResponseEntity<ExampleLike> saveExampleLike(@Uid long uid, @PathVariable long id,
                                                             @RequestBody Boolean like){
        return ServerResponseEntity.success(exampleLikeService.saveExampleLike(uid, id, like));
    }

    @DeleteMapping("/{id}/like")
    public ServerResponseEntity<Void> deleteExampleLike(@PathVariable long id, @Uid long uid){
        exampleLikeService.deleteExampleLike(uid, id);
        return ServerResponseEntity.success();
    }
}
