package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.core.table.CommentDo;
import com.jason.tics.comment.persistence.mybatis.MybatisTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Jason
 */
public class CommentMapperTest extends MybatisTest {
    private CommentMapper commentMapper;

    @Before
    public void init(){
        commentMapper = openSessionByXmlAndProp().getMapper(CommentMapper.class);
    }

    @Test
    public void testGetComment(){
        log.debug("查询评论："+commentMapper.getComment(1));
    }

    @Test
    public void testListComments(){
        log.debug("查询评论："+ Arrays.toString(commentMapper.listComments(new long[]{1, 2})));
    }

    @Test
    public void testListSubjectCommentIds(){
        log.debug("查看主题1的评论Id："+ Arrays.toString(commentMapper.listSubjectCommentIds("1")));
    }

    @Test
    public void testListSubjectComments(){
        log.debug("查看排序分页评论"+ Arrays.toString(commentMapper
                .listSubjectCommentsByColumn("1", null, false, 0, 2)));
    }

    @Test
    public void testListSubjectCommentsByCreateTime(){
        log.debug(""+commentMapper.getComment(5));
        log.debug("查看排序分页评论"+ Arrays.toString(commentMapper.listSubjectCommentsByColumn(
                "1", "create_time", false, 0, 3)));
    }

    @Test
    public void testCountCommentBySubject(){
        log.debug("主题1的评论数量："+commentMapper.countCommentBySubject("1"));
    }

    @Test
    public void testDeleteComments(){
        log.debug("删除多条评论："+commentMapper.deleteComments(new long[]{1, 2}));
    }

    @Test
    public void testAddComment(){
        CommentDo commentDo = new CommentDo();
        commentDo.setContent("新增");
        commentDo.setUid(2L);
        commentDo.setSubjectId("2");
        log.debug(""+commentMapper.addComment(commentDo));
        log.debug("新增的id："+commentDo.getCommentId());
    }

    @Test
    public void testUpdateCommentContent(){
        log.debug("修改评论："+commentMapper.updateCommentContent(2, "修改"));
    }
}
