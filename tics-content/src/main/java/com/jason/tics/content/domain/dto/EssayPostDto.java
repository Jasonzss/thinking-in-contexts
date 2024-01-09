package com.jason.tics.content.domain.dto;

import com.jason.tics.content.domain.EssayPost;
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
public class EssayPostDto {
    private Long uid;
    private String title;
    private String essayUrl;
    private String coverImageUrl;

    public EssayPost getEssayPost(){
        EssayPost essayPost = new EssayPost();
        essayPost.setAuthorId(uid);
        essayPost.setTitle(title);
        essayPost.setEssay(essayUrl);
        essayPost.setCoverImageUrl(coverImageUrl);
        return essayPost;
    }
}
