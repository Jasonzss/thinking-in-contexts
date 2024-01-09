package com.jason.tics.comment.core.dao;

import com.jason.tics.comment.core.Attachment;

/**
 * @author Jason
 */
public interface ReplyAttachmentDao {
    Attachment[] getReplyAttachments(long replyId);

    int saveReplyAttachments(long replyId, String[] attachmentUrls);

    int deleteReplyAttachments(long[] replyIds);
}
