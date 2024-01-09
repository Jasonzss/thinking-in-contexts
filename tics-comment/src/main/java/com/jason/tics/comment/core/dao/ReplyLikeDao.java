package com.jason.tics.comment.core.dao;

import com.jason.tics.comment.core.Like;

/**
 * @author Jason
 */
public interface ReplyLikeDao {

    int getReplyLikeNum(long replyId);

    Like getReplyLikeByUid(long replyId, long uid);

    int addReplyLike(long replyId, long uid);

    int deleteReplyLikeByUid(long replyId, long uid);

    int deleteReplyLikes(long[] replyIds);
}
