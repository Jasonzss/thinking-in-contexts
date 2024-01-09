package com.jason.tics.content.domain.dto;

import com.jason.tics.content.domain.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
public class VideoPostDto {
    private Long uid;
    private String[] tags;
    private String title;
    private String video;
    private String coverImage;
    private String introduction;

    public List<Tag> getTags(String id){
        List<Tag> tagList = new ArrayList<>();
        for (String tag : tags) {
            tagList.add(new Tag(id, tag));
        }
        return tagList;
    }
}
