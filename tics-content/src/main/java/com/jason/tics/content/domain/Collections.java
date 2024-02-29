package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 收藏
 * @author Jason
 */
@Data
@TableName("collections")
public class Collections {
    public static final String DEFAULT_FAVORITE_NAME = "我的喜欢";
    public static final String DEFAULT_COLLECTIONS_NAME = "默认收藏夹";

    @TableId
    private Long collectionsId;
    private String name;
    private Long uid;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
