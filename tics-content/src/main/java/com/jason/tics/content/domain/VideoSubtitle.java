package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 视频字幕
 * @author Jason
 */
@Data
@TableName("video_subtitle")
public class VideoSubtitle {
    @TableId
    private Long videoSubtitleId;
    private String videoId;
    //字幕文件url
    private String subtitleUrl;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
