package com.jason.tics.comment.persistence.mybatis.impl;

import com.jason.tics.comment.core.table.ReplyDo;
import com.jason.tics.comment.core.dao.ReplyDao;
import com.jason.tics.comment.persistence.mybatis.mapper.ReplyMapper;

/**
 * @author Jason
 */
public class ReplyDaoImpl implements ReplyDao {
    private ReplyMapper replyMapper;

    public ReplyDaoImpl(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    @Override
    public ReplyDo getReply(long replyId) {
        return replyMapper.getReply(replyId);
    }

    @Override
    public ReplyDo[] listReplies(long[] replyIds) {
        return replyMapper.listReplies(replyIds);
    }

    @Override
    public long[] listCommentReplyIds(long commentId) {
        return replyMapper.listCommentReplyIds(commentId);
    }

    @Override
    public ReplyDo[] listCommentRepliesByColumn(long commentId, String columnName, boolean ascent, int offset, int limit) {
        return replyMapper.listCommentRepliesByColumn(commentId, columnName, ascent, offset, limit);
    }

    @Override
    public int countCommentReplies(long commentId) {
        return replyMapper.countCommentReplies(commentId);
    }

    @Override
    public int deleteReplies(long[] replyIds) {
        return replyMapper.deleteReplies(replyIds);
    }

    @Override
    public long addReply(long commentId, long uid, String content) {
        return replyMapper.addReply(new ReplyDo(commentId, uid, content));
    }

    @Override
    public int updateReplyContent(long replyId, String content) {
        return replyMapper.updateReplyContent(replyId, content);
    }

    @Override
    public int updateReplyLikeNum(long replyId, int value) {
        int likeNum = replyMapper.getReplyLikeNum(replyId);
        likeNum += value;
        return replyMapper.updateReplyLikeNum(likeNum, replyId);
    }
}
