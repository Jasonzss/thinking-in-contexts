package com.jason.tics.comment.persistence.mybatis;

import com.jason.tics.comment.core.Like;
import com.jason.tics.comment.persistence.ReplyLikeDao;
import com.jason.tics.comment.persistence.mybatis.mapper.ReplyLikeMapper;

/**
 * @author Jason
 */
public class ReplyLikeDaoImpl implements ReplyLikeDao {
    private ReplyLikeMapper replyLikeMapper;

    @Override
    public int getReplyLikeNum(long replyId) {
        return replyLikeMapper.getReplyLikeNum(replyId);
    }

    @Override
    public Like getReplyLikeByUid(long replyId, long uid) {
        return replyLikeMapper.getReplyLikeByUid(replyId, uid);
    }

    @Override
    public int addReplyLike(long replyId, long uid) {
        return replyLikeMapper.addReplyLike(replyId, uid);
    }

    @Override
    public int deleteReplyLikeByUid(long replyId, long uid) {
        return replyLikeMapper.deleteReplyLikeByUid(replyId, uid);
    }

    @Override
    public int deleteReplyLikes(long[] replyIds) {
        return replyLikeMapper.deleteReplyLikes(replyIds);
    }
}
