package com.jason.tics.comment.persistence.mybatis.impl.meme;

import com.jason.tics.comment.core.Like;
import com.jason.tics.comment.meme.dao.CommentMemeLikeDao;
import com.jason.tics.comment.persistence.mybatis.mapper.meme.CommentMemeLikeMapper;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jason
 */
@Getter
@Setter
public class CommentMemeLikeDaoImpl implements CommentMemeLikeDao {
    private CommentMemeLikeMapper commentMemeLikeMapper;

    public CommentMemeLikeDaoImpl(CommentMemeLikeMapper commentMemeLikeMapper) {
        this.commentMemeLikeMapper = commentMemeLikeMapper;
    }

    @Override
    public Like getCommentMemeLike(long commentMemeId, long uid) {
        return commentMemeLikeMapper.getCommentMemeLike(commentMemeId, uid);
    }

    @Override
    public int countCommentMemeLikes(long commentMemeId) {
        return commentMemeLikeMapper.countCommentMemeLikes(commentMemeId);
    }

    @Override
    public int addCommentMemeLike(long commentMemeId, long uid) {
        return commentMemeLikeMapper.addCommentMemeLike(commentMemeId, uid);
    }

    @Override
    public int deleteCommentMemeLikeByUid(long commentMemeId, long uid) {
        return commentMemeLikeMapper.deleteCommentMemeLikeByUid(commentMemeId, uid);
    }

    @Override
    public int deleteCommentMemeLikes(long commentMemeId) {
        return commentMemeLikeMapper.deleteCommentMemeLikes(commentMemeId);
    }
}
