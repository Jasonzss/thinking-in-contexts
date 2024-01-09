package com.jason.tics.comment.persistence.mybatis;

import com.jason.tics.comment.core.dao.*;
import com.jason.tics.comment.persistence.JCommentDaoFactory;
import com.jason.tics.comment.persistence.mybatis.impl.*;
import com.jason.tics.comment.persistence.mybatis.mapper.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Jason
 */
public class MybatisJCommentDaoFactory implements JCommentDaoFactory {
    protected SqlSessionFactory sqlSessionFactory;

    public MybatisJCommentDaoFactory(String driver, String url, String username, String password){
        Properties prop = new Properties();
        prop.put("driver",driver);
        prop.put("url",url);
        prop.put("username",username);
        prop.put("password",password);

        String resource = "jcomment/mybatis-config.xml";

        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, prop);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommentDao getCommentDao() {
        return new CommentDaoImpl(sqlSessionFactory.openSession().getMapper(CommentMapper.class));
    }

    @Override
    public CommentLikeDao getCommentLikeDao() {
        return new CommentLikeDaoImpl(sqlSessionFactory.openSession().getMapper(CommentLikeMapper.class));
    }

    @Override
    public CommentAttachmentDao getCommentAttachmentDao() {
        return new CommentAttachmentDaoImpl(sqlSessionFactory.openSession().getMapper(CommentAttachmentMapper.class));
    }

    @Override
    public ReplyDao getReplyDao() {
        return new ReplyDaoImpl(sqlSessionFactory.openSession().getMapper(ReplyMapper.class));
    }

    @Override
    public ReplyLikeDao getReplyLikeDao() {
        return new ReplyLikeDaoImpl(sqlSessionFactory.openSession().getMapper(ReplyLikeMapper.class));
    }

    @Override
    public ReplyAttachmentDao getReplyAttachmentDao() {
        return new ReplyAttachmentDaoImpl(sqlSessionFactory.openSession().getMapper(ReplyAttachmentMapper.class));
    }
}
