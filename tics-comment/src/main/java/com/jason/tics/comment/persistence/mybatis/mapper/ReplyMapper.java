package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.core.table.ReplyDo;
import org.apache.ibatis.annotations.*;

/**
 * @author Jason
 */
public interface ReplyMapper {
    @Select("select * from reply where reply_id = #{replyId}")
    ReplyDo getReply(long replyId);

    ReplyDo[] listReplies(@Param("replyIds") long[] replyIds);

    @Select("select reply_id from reply where comment_id = #{commentId}")
    long[] listCommentReplyIds(long commentId);

    /**
     *
     * @param ascent 是否顺序排序
     * @param offset 跳过查询的条数
     * @param limit 限制查询的条数
     * @return 指定时间排序下从offset开始往后的limit条数据
     */
    ReplyDo[] listCommentRepliesByColumn(@Param("commentId") long commentId,@Param("columnName") String columnName,@Param("ascent") boolean ascent,@Param("offset") int offset,@Param("limit") int limit);

    @Select("select  count(reply_id) from reply where comment_id = #{commentId}")
    int countCommentReplies(long commentId);

    int deleteReplies(@Param("replyIds") long[] replyIds);

    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "replyId", keyColumn = "reply_id", before = false, resultType = long.class)
    @Insert("insert into reply (comment_id, uid, content, create_time) values (#{commentId}, #{uid}, #{content}, now())")
    long addReply(ReplyDo replyDo);

    @Update("update reply set content = #{content} where reply_id = #{replyId}")
    int updateReplyContent(@Param("replyId") long replyId,@Param("content") String content);

    @Select("select like_num from reply where reply_id = #{replyId}")
    int getReplyLikeNum(long replyId);

    @Update("update reply set like_num = #{likeNum} where reply_id = #{replyId}")
    int updateReplyLikeNum(@Param("likeNum") int likeNum,@Param("replyId") long replyId);
}
