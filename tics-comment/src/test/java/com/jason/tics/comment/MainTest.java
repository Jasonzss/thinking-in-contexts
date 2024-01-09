package com.jason.tics.comment;

import com.jason.tics.comment.core.CommonCommentArea;
import com.jason.tics.comment.persistence.mybatis.MybatisJCommentDaoFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * 从业务使用的角度去测试此框架的从外部使用的情况
 *
 * @author Jason
 */
@Slf4j
public class MainTest {
    CommonCommentArea commentArea;

    @Before
    public void init() {
//        commentArea = CommentAreaBuilder.replyable().withAttachment().build();
        MybatisJCommentDaoFactory mybatisJCommentDaoFactory = new MybatisJCommentDaoFactory(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/tics_comment?useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                "root",
                "123456");

        commentArea = new CommonCommentArea(mybatisJCommentDaoFactory);
    }

    /* 评论相关 */

    @Test
    public void testGetComment(){
        log.debug(""+commentArea.getComment(1));
    }

    @Test
    public void testGetCommentWithReply(){

    }

    @Test
    public void testGetCommentWithReplyAndLike(){

    }

    @Test
    public void testGetCommentWithReplyAndLikeAndAttachment(){

    }

    @Test
    public void testGetCommentWithReplyAndLikeAndAttachmentAndMeme(){

    }

    @Test
    public void testDeleteComment(){

    }

    @Test
    public void testUpdateCommentContent(){

    }

    @Test
    public void testAddComment(){

    }

    @Test
    public void testListCommentPages(){

    }

    @Test
    public void testCountComments(){

    }

    /* 评论点赞相关 */

    @Test
    public void testLikeComment(){

    }

    @Test
    public void testDislikeComment(){

    }

    @Test
    public void testCountCommentLikes(){

    }

    @Test
    public void testIsCommentLiked(){

    }

    /* 回复相关 */

    @Test
    public void testGetReply(){

    }

    @Test
    public void testGetReplyWithAttachment(){

    }

    @Test
    public void testGetReplyWithLike(){

    }

    @Test
    public void testDeleteReply(){

    }

    @Test
    public void testUpdateReplyContent(){

    }

    @Test
    public void testAddReply(){

    }

    @Test
    public void testListReplyPages(){

    }

    @Test
    public void testCountCommentReplies(){

    }

    /* 回复点赞相关 */

    @Test
    public void testCountReplyLikes(){

    }

    @Test
    public void testIsReplyLiked(){

    }

    @Test
    public void testLikeReply(){

    }

    @Test
    public void testDislikeReply(){

    }

    /* 评论meme相关 */

    @Test
    public void testGetMeme(){

    }

    @Test
    public void testListCommentMemes(){

    }

    @Test
    public void testAddCommentMeme(){

    }

    @Test
    public void testCountMemeLikes(){

    }

    @Test
    public void testLikeCommentMeme(){

    }

    @Test
    public void testDislikeCommentMeme(){

    }

    /* meme管理相关 */

    @Test
    public void testListMemes(){

    }

    @Test
    public void testAddMeme(){

    }

    @Test
    public void testDeleteMeme(){

    }

    @Test
    public void testUpdateMemeImage(){

    }

    @Test
    public void testUpdateMemeContent(){

    }
}
