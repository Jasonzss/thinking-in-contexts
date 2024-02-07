package com.jason.tics.dictionary.service;

import com.jason.tics.dictionary.domain.ExampleLike;

/**
 * @author Jason
 */
public interface ExampleLikeService {
    ExampleLike saveExampleLike(long uid, long id, boolean isLike);

    void deleteExampleLike(long uid, long id);
}
