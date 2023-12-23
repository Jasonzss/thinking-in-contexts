package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.core.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jason
 */
public interface ReplyLikeMapper {
    @Select("select count(uid) from reply_like where reply_id = #{replyId}")
    int getReplyLikeNum(long replyId);

    Like getReplyLikeByUid(@Param("replyId") long replyId,@Param("uid") long uid);

    @Insert("insert ignore into reply_like (uid, reply_id, create_time) values (#{uid},#{replyId},now())")
    int addReplyLike(@Param("replyId") long replyId,@Param("uid") long uid);

    @Delete("delete from reply_like where reply_id = #{replyId} and uid = #{uid}")
    int deleteReplyLikeByUid(@Param("replyId") long replyId,@Param("uid") long uid);

    int deleteReplyLikes(@Param("replyIds") long[] replyIds);
}
