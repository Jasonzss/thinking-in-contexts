package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.persistence.mybatis.MybatisTest;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jason
 */
public class ReplyLikeMapperTest extends MybatisTest {
    private ReplyLikeMapper replyLikeMapper;

    @Before
    public void init(){
        replyLikeMapper = openSessionByXmlAndProp().getMapper(ReplyLikeMapper.class);
    }

    @Test
    public void testGetReplyLikeNum(){
        log.debug("reply 1 的点赞数量："+replyLikeMapper.getReplyLikeNum(1));
    }

    @Test
    public void testGetReplyLikeByUid(){
        log.debug("查找user1是否点赞了reply1："+replyLikeMapper.getReplyLikeByUid(1, 1));
    }

    @Test
    public void testAddReplyLike(){
        log.debug("添加点赞个数："+replyLikeMapper.addReplyLike(1, 1));
    }

    @Test
    public void testDeleteReplyLikeByUid(){
        log.debug("删除点赞个数："+replyLikeMapper.deleteReplyLikeByUid(1,1));
        log.debug("删除点赞个数："+replyLikeMapper.deleteReplyLikeByUid(5,1));
    }

    @Test
    public void testDeleteReplyLikes(){
        log.debug("删除点赞个数："+replyLikeMapper.deleteReplyLikes(new long[]{1}));
    }
}
