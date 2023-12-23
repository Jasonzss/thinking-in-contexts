package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.persistence.mybatis.MybatisTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Jason
 */
public class ReplyAttachmentMapperTest extends MybatisTest {
    private ReplyAttachmentMapper replyAttachmentMapper;

    @Before
    public void init(){
        replyAttachmentMapper = openSessionByXml().getMapper(ReplyAttachmentMapper.class);
    }

    @Test
    public void testGetReplyAttachments(){
        log.debug("查询id为1的回复的附件"+ Arrays.toString(replyAttachmentMapper.getReplyAttachments(1)));
    }

    @Test
    public void testSaveReplyAttachments(){
        log.debug("保存附件个数："+ replyAttachmentMapper
                .saveReplyAttachments(2, new String[]{"www.qq.com", "www.woc.com"}));
    }

    @Test
    public void testDeleteReplyAttachments(){
        log.debug("删除附件个数："+ replyAttachmentMapper.deleteReplyAttachments(new long[]{1, 3}));
    }

}
