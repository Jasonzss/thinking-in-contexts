package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
@TableName("audio_text")
public class AudioText {
    @TableId
    private Long audioTextId;
    private String audioId;
    private String audioTextUrl;

    public AudioText() {
    }

    public AudioText(String audioId, String audioTextUrl) {
        this.audioId = audioId;
        this.audioTextUrl = audioTextUrl;
    }
}
