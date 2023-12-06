package com.jason.tics.content.domain.audio;

import com.jason.tics.content.domain.AbstractPost;
import com.jason.tics.content.domain.Cover;

/**
 * @author Jason
 * @since 2023/09/13 - 15:01
 */
public class AudioPost extends AbstractPost {
    private String audioUrl;

    @Override
    public Cover getCover() {
        return new Cover(getTitle(), null, null);
    }
}
