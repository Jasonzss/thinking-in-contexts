package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.persistence.mybatis.MybatisTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Jason
 */
public class CommentAttachmentMapperTest extends MybatisTest {
    private CommentAttachmentMapper commentAttachmentMapper;

    @Before
    public void init(){
        commentAttachmentMapper = openSessionByXml().getMapper(CommentAttachmentMapper.class);
    }

    @Test
    public void testGetCommentAttachments(){
        log.debug("评论的附件如下："+ Arrays.toString(commentAttachmentMapper.getCommentAttachments(1)));
    }

    @Test
    public void testSaveCommentAttachments(){
        log.debug("新增附件个数："+commentAttachmentMapper
                .saveCommentAttachments(2, new String[]{"aaa","bbb","ddd"}));
    }

    @Test
    public void testDeleteCommentAttachments(){
        log.debug("删除附件数量："+commentAttachmentMapper.deleteCommentAttachments(new long[]{1}));
    }

}
