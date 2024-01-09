package com.jason.tics.comment.meme.dao;

import com.jason.tics.comment.meme.CommentMemeDo;

/**
 * @author Jason
 */
public interface CommentMemeDao {
    CommentMemeDo getCommentMeme(long commentMemeId);

    CommentMemeDo[] listCommentMemes(long commentId);

    int addCommentMeme(CommentMemeDo commentMeme);

    int deleteCommentMeme(long commentMemeId);

    int deleteCommentMemes(long commentId);

    int updateCommentMemeLikeNum(long commentMemeId, int value);
}
