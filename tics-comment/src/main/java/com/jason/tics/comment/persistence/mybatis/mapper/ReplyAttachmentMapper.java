package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.core.Attachment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jason
 */
public interface ReplyAttachmentMapper {
    Attachment[] getReplyAttachments(long replyId);

    int saveReplyAttachments(@Param("replyId") long replyId,@Param("attachmentUrls") String[] attachmentUrls);

    int deleteReplyAttachments(@Param("replyIds") long[] replyIds);
}
