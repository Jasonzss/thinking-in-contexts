package com.jason.tics.comment.persistence.mybatis.mapper.meme;

import com.jason.tics.comment.meme.CommentMemeDo;
import org.apache.ibatis.annotations.*;

/**
 * @author Jason
 */
public interface CommentMemeMapper {
    @Select("select * from comment_meme where comment_meme_id = #{commentMemeId}")
    CommentMemeDo getCommentMeme(long commentMemeId);

    @Select("select * from comment_meme where comment_id = #{commentId}")
    CommentMemeDo[] listCommentMemes(long commentId);

    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "commentMemeId", keyColumn = "comment_meme_id", before = false, resultType = long.class)
    @Insert("insert into comment_meme (comment_id, meme_id, create_time) values (#{commentId}, #{memeId}, now())")
    int addCommentMeme(CommentMemeDo commentMeme);

    @Delete("delete from comment_meme where comment_meme_id = #{commentMemeId}")
    int deleteCommentMeme(@Param("commentMemeId") long commentMemeId);

    @Delete("delete from comment_meme where comment_id = #{commentId}")
    int deleteCommentMemes(@Param("commentId") long commentId);

    @Select("select like_num from comment_meme where comment_meme_id = #{commentMemeId}")
    int getCommentMemeLikeNum(long commentMemeId);

    @Update("update comment_meme set like_num = #{likeNum} where comment_meme_id = #{commentMemeId}")
    int updateCommentMemeLikeNum(@Param("likeNum") int likeNum,@Param("commentMemeId") long commentMemeId);
}

