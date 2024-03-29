package com.jason.tics.comment.core;

import com.jason.tics.comment.core.table.ReplyDo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
public class Reply {
    private Long replyId;
    private Long commentId;
    private Long uid;
    private String content;

    private Date createTime;
    private Date updateTime;

    private Attachment[] attachment = null;
    private Integer likeNum = 0;

    public static final String SORT_COLUMN_CREATE_TIME = "create_time";
    public static final String SORT_COLUMN_LIKE_NUM = "like_num";

    public Reply() {
    }

    public Reply(ReplyDo replyDo) {
        setCommentId(replyDo.getCommentId());
        setUid(replyDo.getUid());
        setReplyId(replyDo.getReplyId());
        setContent(replyDo.getContent());
    }
}
