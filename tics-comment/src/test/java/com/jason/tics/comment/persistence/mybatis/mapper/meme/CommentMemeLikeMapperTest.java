package com.jason.tics.comment.persistence.mybatis.mapper.meme;

import com.jason.tics.comment.persistence.mybatis.MybatisTest;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jason
 */
public class CommentMemeLikeMapperTest extends MybatisTest {
    private CommentMemeLikeMapper commentMemeLikeMapper;

    @Before
    public void init(){
        commentMemeLikeMapper = openSessionByXmlAndProp().getMapper(CommentMemeLikeMapper.class);
    }

    @Test
    public void testGetCommentMemeLike(){
        log.debug("获取meme点赞信息：" + commentMemeLikeMapper.getCommentMemeLike(1, 1));
    }

    @Test
    public void testCountCommentMemeLikes(){
        log.debug("获取meme点赞个数：" + commentMemeLikeMapper.countCommentMemeLikes(1));
    }

    @Test
    public void testAddCommentMemeLike(){
        log.debug("点赞评论meme：" + commentMemeLikeMapper.addCommentMemeLike(1, 3));
    }

    @Test
    public void testDeleteCommentMemeLikeByUid(){
        log.debug("删除评论meme点赞：" + commentMemeLikeMapper.deleteCommentMemeLikeByUid(1, 2));
    }

    @Test
    public void testDeleteCommentMemeLikes(){
        log.debug("删除评论1下所有meme点赞：" + commentMemeLikeMapper.deleteCommentMemeLikes(1));
    }
}
