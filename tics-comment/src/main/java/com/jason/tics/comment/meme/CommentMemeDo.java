package com.jason.tics.comment.meme;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Jason
 */
@Getter
@Setter
public class CommentMemeDo {
    private Long commentMemeId;
    private Long commentId;
    private Integer memeId;

    private Date createTime;
    private Date updateTime;

    public CommentMemeDo() {
    }

    public CommentMemeDo(Long commentId, Integer memeId) {
        this.commentId = commentId;
        this.memeId = memeId;
    }

    @Override
    public String toString() {
        return "CommentMemeDo{" +
                "commentMemeId=" + commentMemeId +
                ", commentId=" + commentId +
                ", memeId=" + memeId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
