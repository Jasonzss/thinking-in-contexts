package com.jason.tics.comment.persistence;

import com.jason.tics.comment.core.Attachment;

/**
 * @author Jason
 */
public interface CommentAttachmentDao {
    Attachment[] getCommentAttachments(long commentId);

    void saveCommentAttachments(long commentId, String[] attachmentUrls);

    void deleteCommentAttachments(long[] commentIds);
}
