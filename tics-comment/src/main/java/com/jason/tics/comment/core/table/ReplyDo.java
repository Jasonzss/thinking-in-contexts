package com.jason.tics.comment.core.table;

import com.jason.tics.comment.core.Reply;
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
public class ReplyDo {
    private Long replyId;
    private Long commentId;
    private Long uid;
    private String content;

    private Date createTime;
    private Date updateTime;

    private Integer likeNum;

    public static final String SORT_COLUMN_CREATE_TIME = "create_time";
    public static final String SORT_COLUMN_LIKE_NUM = "like_num";

    public ReplyDo() {
    }

    public ReplyDo(Long commentId, Long uid, String content) {
        this.commentId = commentId;
        this.uid = uid;
        this.content = content;
    }

    public ReplyDo(Reply reply) {
        setUid(reply.getUid());
        setCommentId(reply.getCommentId());
        setContent(reply.getContent());
        setReplyId(reply.getReplyId());
    }
}
