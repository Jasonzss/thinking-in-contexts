package com.jason.tics.comment.meme;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Jason
 */
@Getter
@Setter
public class Meme {
    private Integer memeId;
    private String memeName;
    private String memeImage;

    private Date createTime;
    private Date updateTime;

    public Meme() {
    }

    public Meme(String memeName, String memeImage) {
        this.memeName = memeName;
        this.memeImage = memeImage;
    }

    @Override
    public String toString() {
        return "Meme{" +
                "memeId=" + memeId +
                ", memeName='" + memeName + '\'' +
                ", memeImage='" + memeImage + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
