package com.jason.tics.comment.meme.dao;

import com.jason.tics.comment.core.Like;

/**
 * @author Jason
 */
public interface CommentMemeLikeDao {
    Like getCommentMemeLike(long commentMemeId, long uid);

    int countCommentMemeLikes(long commentMemeId);

    int addCommentMemeLike(long commentMemeId, long uid);

    int deleteCommentMemeLikeByUid(long commentMemeId, long uid);

    int deleteCommentMemeLikes(long commentMemeId);
}
