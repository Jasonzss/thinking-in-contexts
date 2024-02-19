package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 14:58
 */
@Getter
@Setter
@ToString
@TableName("essay")
public class EssayPost {
    private String essay;
    private String coverImageUrl;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * EssayId格式形如：e123456
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String essayId;
    private Long authorId;
    private String title;
    private Long quantityOfViews;
}
