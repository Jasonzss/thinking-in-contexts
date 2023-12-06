package com.jason.tics.content.domain.essay;

import com.jason.tics.content.domain.AbstractPost;
import com.jason.tics.content.domain.Cover;

/**
 * @author Jason
 * @since 2023/09/13 - 14:58
 */
public class EssayPost extends AbstractPost {
    private String essay;

    @Override
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
