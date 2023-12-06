package com.jason.tics.content.domain.video;

import java.io.InputStream;

/**
 * @author Jason
 */
public class VideoPostDto {
    private Long postId;
    private Long channelId;
    private String[] tags;
    private String title;
    private InputStream video;
    private InputStream coverImage;
    private String introduction;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InputStream getVideo() {
        return video;
    }

    public void setVideo(InputStream video) {
        this.video = video;
    }

    public InputStream getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(InputStream coverImage) {
        this.coverImage = coverImage;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
