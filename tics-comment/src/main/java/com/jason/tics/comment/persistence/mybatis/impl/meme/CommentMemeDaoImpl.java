package com.jason.tics.comment.persistence.mybatis.impl.meme;

import com.jason.tics.comment.meme.CommentMemeDo;
import com.jason.tics.comment.meme.dao.CommentMemeDao;
import com.jason.tics.comment.persistence.mybatis.mapper.meme.CommentMemeMapper;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jason
 */
@Getter
@Setter
public class CommentMemeDaoImpl implements CommentMemeDao {
    private CommentMemeMapper commentMemeMapper;

    public CommentMemeDaoImpl(CommentMemeMapper commentMemeMapper) {
        this.commentMemeMapper = commentMemeMapper;
    }

    @Override
    public CommentMemeDo getCommentMeme(long commentMemeId) {
        return commentMemeMapper.getCommentMeme(commentMemeId);
    }

    @Override
    public CommentMemeDo[] listCommentMemes(long commentId) {
        return commentMemeMapper.listCommentMemes(commentId);
    }

    @Override
    public int addCommentMeme(CommentMemeDo commentMeme) {
        return commentMemeMapper.addCommentMeme(commentMeme);
    }

    @Override
    public int deleteCommentMeme(long commentMemeId) {
        return commentMemeMapper.deleteCommentMeme(commentMemeId);
    }

    @Override
    public int deleteCommentMemes(long commentId) {
        return commentMemeMapper.deleteCommentMemes(commentId);
    }

    @Override
    public int updateCommentMemeLikeNum(long replyId, int value) {
        int likeNum = commentMemeMapper.getCommentMemeLikeNum(replyId);
        likeNum += value;
        return commentMemeMapper.updateCommentMemeLikeNum(likeNum, replyId);
    }
}
