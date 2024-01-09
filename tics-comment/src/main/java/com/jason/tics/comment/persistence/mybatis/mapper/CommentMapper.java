package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.core.table.CommentDo;
import org.apache.ibatis.annotations.*;

/**
 * @author Jason
 */
public interface CommentMapper {
    @Select("select * from comment where comment_id = #{commentId}")
    CommentDo getComment(long commentId);

    CommentDo[] listComments(@Param("commentIds") long[] commentIds);

    @Select("select comment_id from comment where subject_id = #{subjectId}")
    long[] listSubjectCommentIds(String subjectId);

    /**
     *
     * @param ascent 是否顺序排序
     * @param offset 跳过查询的条数
     * @param limit 限制查询的条数
     * @return 指定时间排序下从offset开始往后的limit条数据
     */
    CommentDo[] listSubjectCommentsByColumn(@Param("subjectId") String subjectId,@Param("columnName") String columnName,@Param("ascent") boolean ascent,@Param("offset") int offset,@Param("limit") int limit);

    @Select("select  count(comment_id) from comment where subject_id = #{subject_id}")
    int countCommentBySubject(String subjectId);

    int deleteComments(@Param("commentIds") long[] commentIds);

    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "commentId", keyColumn = "comment_id", before = false, resultType = long.class)
    @Insert("insert into comment (subject_id, uid, content, create_time) values (#{subjectId}, #{uid}, #{content}, now())")
    long addComment(CommentDo commentDo);

    @Update("update comment set content = #{content} where comment_id = #{commentId}")
    int updateCommentContent(@Param("commentId") long commentId,@Param("content") String content);

    @Select("select like_num from comment where comment_id = #{commentId}")
    int getCommentLikeNum(long commentId);

    @Update("update comment set like_num = #{likeNum} where comment_id = #{commentId}")
    int updateCommentLikeNum(@Param("likeNum") int likeNum,@Param("commentId") long commentId);
}
