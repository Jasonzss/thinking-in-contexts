package com.jason.tics.api.content.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 15:00
 */
@Data
public class VideoPostBo {
    private String videoUrl;
    private String introduction;
    private String coverImageUrl;

    private Date createTime;
    private Date updateTime;

    private String videoId;
    private Long authorId;
    private String title;
    private Long quantityOfViews;
}
