package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Jason
 * @since 2023/09/13 - 15:01
 */
@Getter
@Setter
@ToString
@TableName("audio")
public class AudioPost {
    private String audioUrl;
    private String introduction;
    private String coverImageUrl;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * AudioId格式形如：a123456
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String audioId;
    private Long authorId;
    private String title;
    private Long quantityOfViews;

    @TableField(exist = false)
    private List<String> tags;
}
