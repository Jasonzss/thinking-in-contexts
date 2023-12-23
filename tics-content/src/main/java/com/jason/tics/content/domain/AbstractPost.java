package com.jason.tics.content.domain;

import com.jason.tics.common.entity.DatedEntity;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 * @since 2023/09/13 - 15:07
 */
public abstract class AbstractPost extends DatedEntity {
    private Long postId;
    private Long authorId;
    private String[] tags;
    private String title;
    private Long quantityOfViews;

    public Long getPostId() {
        return postId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

    public Long getQuantityOfViews() {
        return quantityOfViews;
    }

    public void setQuantityOfViews(Long quantityOfViews) {
        this.quantityOfViews = quantityOfViews;
    }
}
