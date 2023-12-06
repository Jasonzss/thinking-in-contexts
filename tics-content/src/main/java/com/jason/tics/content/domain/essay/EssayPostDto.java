package com.jason.tics.content.domain.essay;

/**
 * @author Jason
 */
public class EssayPostDto {
    private Long postId;
    private Long channelId;
    private String[] tags;
    private String title;
    private String essay;

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

    public String getEssay() {
        return essay;
    }

    public void setEssay(String essay) {
        this.essay = essay;
    }
}
