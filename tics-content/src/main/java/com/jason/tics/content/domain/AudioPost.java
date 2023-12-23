package com.jason.tics.content.domain;

import com.jason.tics.content.domain.AbstractPost;
import com.jason.tics.content.domain.Cover;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 15:01
 */
public class AudioPost extends AbstractPost {
    private String audioUrl;

    private Date createDate;
    private Date updateDate;

    private Long postId;
    private Long authorId;
    private String[] tags;
    private String title;
    private Long quantityOfViews;

    public Cover getCover() {
        return new Cover(getTitle(), null, null);
    }
}
