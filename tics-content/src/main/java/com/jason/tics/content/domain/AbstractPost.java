package com.jason.tics.content.domain;

import com.jason.tics.common.entity.DatedEntity;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 * @since 2023/09/13 - 15:07
 */
@Component
public abstract class AbstractPost extends DatedEntity implements Post {
    private Long postId;
    private Long ownerId;
    private Long channelId;
    private Long[] tagIds;
    private String title;
    private Long quantityOfViews;

    @Override
    public Long getPostId() {
        return postId;
    }

    @Override
    public Long getOwnerId() {
        return ownerId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(Long[] tagIds) {
        this.tagIds = tagIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getQuantityOfViews() {
        return quantityOfViews;
    }

    public void setQuantityOfViews(Long quantityOfViews) {
        this.quantityOfViews = quantityOfViews;
    }
}
