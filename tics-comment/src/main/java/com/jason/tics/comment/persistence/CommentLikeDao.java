package com.jason.tics.comment.persistence;

import com.jason.tics.comment.core.Like;

public interface CommentLikeDao {
    int getCommentLikeNum(long commentId);

    Like getCommentLikeByUid(long commentId, long uid);

    void addCommentLike(long commentId, long uid);

    void deleteCommentLikeById(long commentId, long uid);

    void deleteCommentLikes(long[] commentId);
}
