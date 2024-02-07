package com.jason.tics.dictionary.service.impl;

import com.jason.tics.dictionary.domain.Example;
import com.jason.tics.dictionary.domain.ExampleLike;
import com.jason.tics.dictionary.repository.ExampleLikeRepository;
import com.jason.tics.dictionary.repository.ExampleRepository;
import com.jason.tics.dictionary.service.ExampleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 没有缓存层的点赞业务实现
 * @author Jason
 */
@Service
public class ExampleLikeServiceImpl implements ExampleLikeService {
    @Autowired
    private ExampleLikeRepository exampleLikeRepository;
    @Autowired
    private ExampleRepository exampleRepository;

    @Override
    public ExampleLike saveExampleLike(long uid, long id, boolean isLike) {
        updateLikeNum(id, isLike);
        return exampleLikeRepository.save(new ExampleLike(uid, id, isLike));
    }

    @Override
    public void deleteExampleLike(long uid, long id) {
        ExampleLike.ExampleLikeId exampleLikeId = new ExampleLike.ExampleLikeId(uid, id);
        ExampleLike byId = exampleLikeRepository.getById(exampleLikeId);
        exampleLikeRepository.deleteById(exampleLikeId);
        updateLikeNum(id, byId.getIsLike());
    }

    private void updateLikeNum(long id, boolean isLike){
        Example byId = exampleRepository.getById(id);
        if (isLike) {
            byId.likeNumUpdate(1);
        }else {
            byId.likeNumUpdate(-1);
        }
        exampleRepository.save(byId);
    }
}
