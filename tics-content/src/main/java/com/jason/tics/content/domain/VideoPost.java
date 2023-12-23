package com.jason.tics.content.domain;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 15:00
 */
public class VideoPost extends AbstractPost {
    private String videoUrl;
    private String introduction;
    private String coverImageUrl;

    private Date createDate;
    private Date updateDate;

    private Long postId;
    private Long authorId;
    private String[] tags;
    private String title;
    private Long quantityOfViews;

    public Cover getCover() {
        return new Cover(getTitle(), getCoverImageUrl(), getIntroduction());
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
