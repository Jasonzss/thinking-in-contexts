package com.jason.tics.comment.core.dao;

import com.jason.tics.comment.core.Like;

public interface CommentLikeDao {
    int getCommentLikeNum(long commentId);

    Like getCommentLikeByUid(long commentId, long uid);

    int addCommentLike(long commentId, long uid);

    int deleteCommentLikeById(long commentId, long uid);

    void deleteCommentLikes(long[] commentId);
}
