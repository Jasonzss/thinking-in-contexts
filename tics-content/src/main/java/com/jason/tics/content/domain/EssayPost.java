package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
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

    private Date createTime;
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
