package com.jason.tics.content.domain.video;

import com.jason.tics.content.domain.AbstractPost;
import com.jason.tics.content.domain.Cover;

/**
 * @author Jason
 * @since 2023/09/13 - 15:00
 */
public class VideoPost extends AbstractPost {
    private String videoUrl;
    private String introduction;
    private String coverImageUrl;

    @Override
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
