package com.jason.tics.api.content.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 14:58
 */
@Data
public class EssayPostBo {
    private String essayUrl;
    private String coverImageUrl;
    private String essayId;
    private Long authorId;
    private String title;
    private Long quantityOfViews;

    private Date createTime;
    private Date updateTime;
}
