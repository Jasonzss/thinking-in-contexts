package com.jason.tics.comment.persistence;

import com.jason.tics.comment.meme.dao.CommentMemeDao;
import com.jason.tics.comment.meme.dao.CommentMemeLikeDao;
import com.jason.tics.comment.meme.dao.MemeDao;

/**
 * @author Jason
 */
public interface MemeDaoFactory extends JCommentDaoFactory {
    CommentMemeDao getCommentMemeDao();

    CommentMemeLikeDao getCommentMemeLikeDao();

    MemeDao getMemeDao();
}
