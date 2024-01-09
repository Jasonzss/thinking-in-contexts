package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Jason
 */
@Setter
@Getter
@ToString
@TableName("video_subtitle")
public class VideoSubtitle {
    @TableId
    private Long videoSubtitleId;
    private String videoId;
    private String subtitleUrl;
}
