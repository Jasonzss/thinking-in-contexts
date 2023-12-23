package com.jason.tics.content.resource;

import com.jason.tics.content.dto.AudioPostDto;
import com.jason.tics.content.domain.AudioPost;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RequestMapping("audio")
public class AudioPostResource {
    @GetMapping("/{id}")
    public AudioPost getPost(@PathVariable long id){
        return null;
    }

    @PostMapping
    public AudioPost addPost(AudioPostDto audioPostDto){
        return null;
    }

    @PutMapping("/{id}")
    public AudioPost updatePost(AudioPostDto audioPostDto){
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePost(){

    }

    @GetMapping("/{id}/intro")
    public AudioPost getPostIntro(@PathVariable long id){
        return null;
    }
}
