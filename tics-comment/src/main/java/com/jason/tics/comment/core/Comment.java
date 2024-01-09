package com.jason.tics.comment.core;

import com.jason.tics.comment.core.table.CommentDo;
import com.jason.tics.comment.core.table.ReplyDo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 单条评论的抽象基类，可根据需要的评论内容类型选择对应的子类
 *
 * @author Jason
 */
@Getter
@Setter
@ToString
public class Comment {
    private String subjectId;
    private Long commentId;
    private Long uid;
    private String content;

    private Date createTime;
    private Date updateTime;

    private ReplyDo[] replies;
    private Attachment[] attachment = null;
    private Integer likeNum = 0;

    public static final String SORT_COLUMN_CREATE_TIME = "create_time";
    public static final String SORT_COLUMN_LIKE_NUM = "like_num";

    public Comment() {
    }

    public Comment(CommentDo commentDo) {
        setCommentId(commentDo.getCommentId());
        setUid(commentDo.getUid());
        setSubjectId(commentDo.getSubjectId());
        setContent(commentDo.getContent());
    }
}
