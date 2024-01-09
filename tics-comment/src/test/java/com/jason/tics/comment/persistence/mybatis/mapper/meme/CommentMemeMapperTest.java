package com.jason.tics.comment.persistence.mybatis.mapper.meme;

import com.jason.tics.comment.meme.CommentMemeDo;
import com.jason.tics.comment.persistence.mybatis.MybatisTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Jason
 */
public class CommentMemeMapperTest extends MybatisTest {
    private CommentMemeMapper commentMemeMapper;

    @Before
    public void init(){
        commentMemeMapper = openSessionByXmlAndProp().getMapper(CommentMemeMapper.class);
    }

    @Test
    public void testGetCommentMemeLike(){
        log.debug("获取评论meme："+commentMemeMapper.getCommentMeme(1));
    }

    @Test
    public void testListCommentMemes(){
        log.debug("获取评论1下所有meme："+ Arrays.toString(commentMemeMapper.listCommentMemes(1)));
    }

    @Test
    public void testAddCommentMeme(){
        CommentMemeDo commentMemeDo = new CommentMemeDo(1L, 1);
        commentMemeMapper.addCommentMeme(commentMemeDo);
        log.debug("新增comment meme id："+ commentMemeDo.getCommentMemeId());
    }

    @Test
    public void testDeleteCommentMeme(){
        log.debug("删除comment meme："+commentMemeMapper.deleteCommentMeme(1));
    }

    @Test
    public void testDeleteCommentMemes(){
        log.debug("删除comment meme："+commentMemeMapper.deleteCommentMemes(1));
    }
}
