package com.jason.tics.content.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
public class AudioPostDto {
    private String audioId;
    private String title;
    private String audioUrl;
    private String coverImageUrl;
    private String introduction;
}
