package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 15:00
 */
@Getter
@Setter
@ToString
@TableName("video")
public class VideoPost {
    private String videoUrl;
    private String introduction;
    private String coverImageUrl;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * EssayId格式形如：v123456
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String videoId;
    private Long authorId;
    private String title;
    //TODO 三个浏览量要单独移到一个表中
    private Long quantityOfViews = 0L;
}
