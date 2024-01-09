package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.persistence.mybatis.MybatisTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Jason
 */
public class CommentLikeMapperTest extends MybatisTest {

    private CommentLikeMapper commentLikeMapper;

    @Before
    public void init() {
        commentLikeMapper = openSessionByXmlAndProp().getMapper(CommentLikeMapper.class);
    }

    @Test
    public void testGetCommentLikeNum(){
        log.debug("查询获赞数："+ commentLikeMapper.getCommentLikeNum(1));
        log.debug("查询获赞数："+ commentLikeMapper.getCommentLikeNum(2));
    }

    @Test
    public void testGetCommentLikeByUid(){
        log.debug("查询点赞信息："+ commentLikeMapper.getCommentLikeByUid(1,1));
        log.debug("查询点赞信息："+ commentLikeMapper.getCommentLikeByUid(2,2));
    }

    @Test
    public void testAddCommentLike(){
        log.debug("查询获赞数："+ commentLikeMapper.getCommentLikeNum(123));
        log.debug(""+commentLikeMapper.addCommentLike(123, 1223));
        log.debug("查询获赞数："+ commentLikeMapper.getCommentLikeNum(123));
    }

    @Test
    public void testDeleteCommentLikeById(){
        log.debug("删除行数："+commentLikeMapper.deleteCommentLikeById(1,1));
    }

    @Test
    public void testDeleteCommentLikes(){
        log.debug("删除行数："+commentLikeMapper.deleteCommentLikes(new long[]{1}));
    }

}
