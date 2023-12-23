package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.core.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jason
 */
public interface CommentLikeMapper {
    @Select("select count(uid) from comment_like where comment_id = #{commentId}")
    int getCommentLikeNum(long commentId);

    Like getCommentLikeByUid(@Param("commentId") long commentId, @Param("uid") long uid);

    @Insert("insert ignore into comment_like (uid, comment_id, create_time) values (#{uid},#{commentId},now())")
    int addCommentLike(@Param("commentId")long commentId, @Param("uid") long uid);

    @Delete("delete from comment_like where comment_id = #{commentId} and uid = #{uid}")
    int deleteCommentLikeById(@Param("commentId")long commentId, @Param("uid") long uid);

    int deleteCommentLikes(@Param("commentIds")long[] commentIds);
}
