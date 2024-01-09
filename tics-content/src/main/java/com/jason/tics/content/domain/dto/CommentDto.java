package com.jason.tics.content.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
public class CommentDto {
    private String subjectId;
    private Long uid;
    private String content;
    private MultipartFile[] attachments;
}
