package com.jason.tics.comment.persistence.mybatis;

import com.jason.tics.comment.meme.dao.CommentMemeDao;
import com.jason.tics.comment.meme.dao.CommentMemeLikeDao;
import com.jason.tics.comment.meme.dao.MemeDao;
import com.jason.tics.comment.persistence.MemeDaoFactory;
import com.jason.tics.comment.persistence.mybatis.impl.meme.CommentMemeDaoImpl;
import com.jason.tics.comment.persistence.mybatis.impl.meme.CommentMemeLikeDaoImpl;
import com.jason.tics.comment.persistence.mybatis.impl.meme.MemeDaoImpl;
import com.jason.tics.comment.persistence.mybatis.mapper.meme.CommentMemeLikeMapper;
import com.jason.tics.comment.persistence.mybatis.mapper.meme.CommentMemeMapper;
import com.jason.tics.comment.persistence.mybatis.mapper.meme.MemeMapper;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author Jason
 */
public class MybatisMemeDaoFactory extends MybatisJCommentDaoFactory implements MemeDaoFactory {

    public MybatisMemeDaoFactory(String driver, String url, String username, String password){
        super(driver, url, username, password);
    }

    @Override
    public CommentMemeDao getCommentMemeDao() {
        return new CommentMemeDaoImpl(sqlSessionFactory.openSession().getMapper(CommentMemeMapper.class));
    }

    @Override
    public CommentMemeLikeDao getCommentMemeLikeDao() {
        return new CommentMemeLikeDaoImpl(sqlSessionFactory.openSession().getMapper(CommentMemeLikeMapper.class));
    }

    @Override
    public MemeDao getMemeDao() {
        return new MemeDaoImpl(sqlSessionFactory.openSession().getMapper(MemeMapper.class));
    }
}
