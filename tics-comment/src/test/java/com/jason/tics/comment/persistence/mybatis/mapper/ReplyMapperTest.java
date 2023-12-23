package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.core.ReplyDo;
import com.jason.tics.comment.persistence.mybatis.MybatisTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Jason
 */
public class ReplyMapperTest extends MybatisTest {
    private ReplyMapper replyMapper;

    @Before
    public void init(){
        replyMapper = openSessionByXml().getMapper(ReplyMapper.class);
    }

    @Test
    public void testGetReply(){
        log.debug("查询回复："+replyMapper.getReply(1));
    }

    @Test
    public void testListReplies(){
        log.debug("查询多条回复："+ Arrays.toString(replyMapper.listReplies(new long[]{1,2})));
    }

    @Test
    public void testListCommentReplyIds(){
        log.debug("查询评论下的回复："+ Arrays.toString(replyMapper.listCommentReplyIds(1)));
    }

    @Test
    public void testListCommentReplies(){
        log.debug("查询评论下的回复："+ Arrays.toString(replyMapper
                .listCommentRepliesByColumn(1, null, false, 0, 2)));
    }

    @Test
    public void testListCommentRepliesByCreateTime(){
        log.debug("按发布日期查询评论下的回复："+ Arrays.toString(replyMapper
                .listCommentRepliesByColumn(1, "create_time", false, 0, 2)));
    }

    @Test
    public void testCountCommentReplies(){
        log.debug("查询评论下的回复量："+ replyMapper.countCommentReplies(1));
    }

    @Test
    public void testDeleteReplies(){
        log.debug("删除的回复量："+ replyMapper.deleteReplies(new long[]{1,2}));
    }

    @Test
    public void testAddReply(){
        ReplyDo replyDo = new ReplyDo(1L,2L,"新增");
        log.debug("新增的回复量："+ replyMapper.addReply(replyDo));
        log.debug("新增回复的主键："+replyDo.getReplyId());
    }

    @Test
    public void testUpdateReplyContent(){
        log.debug("修改回复量："+ replyMapper.updateReplyContent(1, "修改"));
        log.debug("修改的回复量："+ replyMapper.updateReplyContent(8, "修改"));
    }



}
