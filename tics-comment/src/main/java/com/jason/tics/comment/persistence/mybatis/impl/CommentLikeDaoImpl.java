package com.jason.tics.comment.persistence.mybatis.impl;

import com.jason.tics.comment.core.Like;
import com.jason.tics.comment.core.dao.CommentLikeDao;
import com.jason.tics.comment.persistence.mybatis.mapper.CommentLikeMapper;

/**
 * @author Jason
 */
public class CommentLikeDaoImpl implements CommentLikeDao {
    private CommentLikeMapper commentLikeMapper;

    public CommentLikeDaoImpl(CommentLikeMapper commentLikeMapper) {
        this.commentLikeMapper = commentLikeMapper;
    }

    @Override
    public int getCommentLikeNum(long commentId) {
        return commentLikeMapper.getCommentLikeNum(commentId);
    }

    @Override
    public Like getCommentLikeByUid(long commentId, long uid) {
        return commentLikeMapper.getCommentLikeByUid(commentId,uid);
    }

    @Override
    public int addCommentLike(long commentId, long uid) {
        return commentLikeMapper.addCommentLike(commentId,uid);
    }

    @Override
    public int deleteCommentLikeById(long commentId, long uid) {
        return commentLikeMapper.deleteCommentLikeById(commentId, uid);
    }

    @Override
    public void deleteCommentLikes(long[] commentId) {
        commentLikeMapper.deleteCommentLikes(commentId);
    }

    public CommentLikeMapper getApprovalMapper() {
        return commentLikeMapper;
    }

    public void setApprovalMapper(CommentLikeMapper commentLikeMapper) {
        this.commentLikeMapper = commentLikeMapper;
    }
}
