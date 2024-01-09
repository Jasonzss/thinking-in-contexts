package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

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

    private Date createTime;
    private Date updateTime;

    /**
     * EssayId格式形如：v123456
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String videoId;
    private Long authorId;
    private String title;
    private Long quantityOfViews;
    @TableField(exist = false)
    private List<String> tags;
}
