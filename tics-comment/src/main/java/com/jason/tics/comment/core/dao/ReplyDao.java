package com.jason.tics.comment.core.dao;

import com.jason.tics.comment.core.table.ReplyDo;

/**
 * @author Jason
 */
public interface ReplyDao {
    ReplyDo getReply(long replyId);

    ReplyDo[] listReplies(long[] replyIds);

    long[] listCommentReplyIds(long commentId);

    /**
     *
     * @param ascent 是否顺序排序
     * @param offset 跳过查询的条数
     * @param limit 限制查询的条数
     * @return 指定时间排序下从offset开始往后的limit条数据
     */
    ReplyDo[] listCommentRepliesByColumn(long commentId, String columnName, boolean ascent, int offset, int limit);

    int countCommentReplies(long commentId);

    int deleteReplies(long[] replyIds);

    long addReply(long commentId, long uid, String content);

    int updateReplyContent(long replyId, String content);

    int updateReplyLikeNum(long replyId, int value);
}
