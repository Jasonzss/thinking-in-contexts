package com.jason.tics.content.domain;

/**
 * @author Jason
 */
public class Cover {
    private String title;
    private String coverImageUrl;
    private String introduction;

    public Cover() {
    }

    public Cover(String title, String coverImageUrl, String introduction) {
        this.title = title;
        this.coverImageUrl = coverImageUrl;
        this.introduction = introduction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
