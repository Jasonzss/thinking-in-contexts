package com.jason.tics.comment.domain.meme;

/**
 * @author Jason
 */
public class Meme {
    private String memeId;
    private Integer approveCount;

    public String getMemeId() {
        return memeId;
    }

    public void setMemeId(String memeId) {
        this.memeId = memeId;
    }

    public Integer getApproveCount() {
        return approveCount;
    }

    public void setApproveCount(Integer approveCount) {
        this.approveCount = approveCount;
    }
}
