package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 14:58
 */
@Data
@TableName("essay")
public class EssayPost {
    private String essayUrl;
    private String coverImageUrl;

    /**
     * EssayId格式形如：e123456
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String essayId;
    private Long authorId;
    private String title;
    private Long quantityOfViews = 0L;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
