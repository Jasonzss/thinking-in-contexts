package com.jason.tics.comment.persistence.mybatis;

import com.jason.tics.comment.core.Like;
import com.jason.tics.comment.persistence.CommentLikeDao;
import com.jason.tics.comment.persistence.mybatis.mapper.CommentLikeMapper;

/**
 * @author Jason
 */
public class CommentLikeDaoImpl implements CommentLikeDao {
    private CommentLikeMapper commentLikeMapper;

    @Override
    public int getCommentLikeNum(long commentId) {
        return commentLikeMapper.getCommentLikeNum(commentId);
    }

    @Override
    public Like getCommentLikeByUid(long commentId, long uid) {
        return commentLikeMapper.getCommentLikeByUid(commentId,uid);
    }

    @Override
    public void addCommentLike(long commentId, long uid) {
        commentLikeMapper.addCommentLike(commentId,uid);
    }

    @Override
    public void deleteCommentLikeById(long commentId, long uid) {
        commentLikeMapper.deleteCommentLikeById(commentId, uid);
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
