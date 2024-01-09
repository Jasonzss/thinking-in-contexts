package com.jason.tics.comment.persistence.mybatis.mapper.meme;

import com.jason.tics.comment.core.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jason
 */
public interface CommentMemeLikeMapper {
    Like getCommentMemeLike(long commentMemeId, long uid);

    @Select("select count(uid) from comment_meme_like where comment_meme_id = #{commentMemeId}")
    int countCommentMemeLikes(long commentMemeId);

    @Insert("insert into comment_meme_like value (#{commentMemeId}, #{uid}, now())")
    int addCommentMemeLike(@Param("commentMemeId") long commentMemeId,@Param("uid") long uid);

    @Delete("delete from comment_meme_like where comment_meme_id = #{commentMemeId} and uid = #{uid}")
    int deleteCommentMemeLikeByUid(@Param("commentMemeId") long commentMemeId,@Param("uid") long uid);

    @Delete("delete from comment_meme_like where comment_meme_id = #{commentMemeId}")
    int deleteCommentMemeLikes(@Param("commentMemeId") long commentMemeId);
}
