package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 收藏的内容
 * @author Jason
 */
@Data
@TableName("collection")
public class Collection {
    private Long uid;
    private String contentId;
    private Long collectionsId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    public Collection() {
    }

    public Collection(Long uid, String contentId, Long collectionsId) {
        this.uid = uid;
        this.contentId = contentId;
        this.collectionsId = collectionsId;
    }
}
