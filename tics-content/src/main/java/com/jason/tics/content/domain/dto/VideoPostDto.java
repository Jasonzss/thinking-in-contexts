package com.jason.tics.content.domain.dto;

import lombok.Data;

/**
 * @author Jason
 */
@Data
public class VideoPostDto {
    private String title;
    private String videoUrl;
    private String coverImageUrl;
    private String introduction;
}
