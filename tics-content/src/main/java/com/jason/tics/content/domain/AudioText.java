package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 */
@Data
@TableName("audio_text")
public class AudioText {
    @TableId
    private Long audioTextId;
    private String audioId;
    private String audioTextUrl;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    public AudioText() {
    }

    public AudioText(String audioId, String audioTextUrl) {
        this.audioId = audioId;
        this.audioTextUrl = audioTextUrl;
    }
}
