package com.jason.tics.comment.core.table;

import com.jason.tics.comment.core.Comment;
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
public class CommentDo {
    private String subjectId;
    private Long commentId;
    private Long uid;
    private String content;

    private Date createTime;
    private Date updateTime;

    private Integer likeNum;

    public static final String SORT_COLUMN_CREATE_TIME = "create_time";
    public static final String SORT_COLUMN_LIKE_NUM = "like_num";

    public CommentDo() {
    }

    public CommentDo(Comment comment) {
        setUid(comment.getUid());
        setSubjectId(comment.getSubjectId());
        setContent(comment.getContent());
        setCommentId(comment.getCommentId());
    }
}
