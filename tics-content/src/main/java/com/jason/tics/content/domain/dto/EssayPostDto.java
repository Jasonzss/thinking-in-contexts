package com.jason.tics.content.domain.dto;

import com.jason.tics.content.domain.EssayPost;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
public class EssayPostDto {
    private String title;
    private String essayUrl;
    private String coverImageUrl;

    public EssayPost getEssayPost(){
        EssayPost essayPost = new EssayPost();
        essayPost.setTitle(title);
        essayPost.setEssayUrl(essayUrl);
        essayPost.setCoverImageUrl(coverImageUrl);
        return essayPost;
    }
}
