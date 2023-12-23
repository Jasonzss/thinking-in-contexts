package com.jason.tics.comment.persistence;

import com.jason.tics.comment.core.CommentDo;

/**
 * 评论持久化的顶级API
 *
 * @author Jason
 */
public interface CommentDao {
    CommentDo getComment(long commentId);

    CommentDo[] listComments(long[] commentIds);

    long[] listSubjectCommentIds(long subjectId);

    /**
     *
     * @param ascent 是否顺序排序
     * @param offset 跳过查询的条数
     * @param limit 限制查询的条数
     * @return 指定时间排序下从offset开始往后的limit条数据
     */
    CommentDo[] listSubjectCommentsByColumn(long subjectId, String columnName, boolean ascent, int offset, int limit);

    int countCommentBySubject(long subjectId);

    int deleteComments(long[] commentIds);

    long addComment(long subjectId, long uid, String content);

    int updateCommentContent(long commentId, String content);
}
