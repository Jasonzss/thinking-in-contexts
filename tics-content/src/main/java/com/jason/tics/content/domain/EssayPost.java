package com.jason.tics.content.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 14:58
 */
@Data
public class EssayPost {
    private Date createDate;
    private Date updateDate;

    private Long postId;
    private Long authorId;
    private String[] tags;
    private String title;
    private Long quantityOfViews;

    private String essay;

    public Cover getCover() {
        String introduction = essay.length() > 50 ? essay.substring(0, 50)+"..." : essay;
        return new Cover(getTitle(), null, introduction);
    }

    public String getEssay() {
        return essay;
    }

    public void setEssay(String essay) {
        this.essay = essay;
    }
}
