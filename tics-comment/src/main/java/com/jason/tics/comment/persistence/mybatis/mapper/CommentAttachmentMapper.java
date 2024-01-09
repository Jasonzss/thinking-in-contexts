package com.jason.tics.comment.persistence.mybatis.mapper;

import com.jason.tics.comment.core.Attachment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jason
 */
public interface CommentAttachmentMapper {
    Attachment[] getCommentAttachments(long commentId);

    int saveCommentAttachments(@Param("commentId")long commentId,@Param("attachmentUrls") String[] attachmentUrls);

    int deleteCommentAttachments(@Param("commentIds")long[] commentIds);

    void deleteCommentAttachmentsByUrlAndId(@Param("commentIds") long commentIds,@Param("attachments")  String[] attachments);
}
