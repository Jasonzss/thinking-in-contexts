package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 15:01
 */
@Data
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
    private Long quantityOfViews = 0L;
}
