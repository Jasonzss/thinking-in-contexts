package com.jason.tics.content.resource;

import com.jason.tics.content.domain.video.VideoPostDto;
import com.jason.tics.content.domain.video.VideoPost;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RequestMapping("/video")
public class VideoPostResource {
    @GetMapping("/{id}")
    public VideoPost getPost(@PathVariable long id){
        return null;
    }

    @PostMapping
    public VideoPost addPost(VideoPostDto videoPostDto){
        return null;
    }

    @PutMapping("/{id}")
    public VideoPost updatePost(VideoPostDto videoPostDto){
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePost(){

    }
}
