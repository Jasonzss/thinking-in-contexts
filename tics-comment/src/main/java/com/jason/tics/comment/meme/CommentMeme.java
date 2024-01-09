package com.jason.tics.comment.meme;

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
public class CommentMeme {
    private Long commentId;
    private Long commentMemeId;
    private Integer memeId;

    private Date createTime;
    private Date updateTime;

    private String memeName;
    private String memeImage;

    private Integer likeNum;

    public CommentMeme() {
    }

    public CommentMeme(CommentMemeDo commentMemeDo, Meme meme, int likeNum) {
        this.commentId = commentMemeDo.getCommentId();
        this.commentMemeId = commentMemeDo.getCommentMemeId();
        this.memeId = commentMemeDo.getMemeId();
        this.createTime = commentMemeDo.getCreateTime();
        this.updateTime = commentMemeDo.getUpdateTime();

        this.memeName = meme.getMemeName();
        this.memeImage = meme.getMemeImage();

        this.likeNum = likeNum;
    }
}
