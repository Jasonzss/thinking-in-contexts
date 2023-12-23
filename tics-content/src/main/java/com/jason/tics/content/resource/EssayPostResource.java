package com.jason.tics.content.resource;

import com.jason.tics.content.domain.EssayPost;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RequestMapping("essay")
public class EssayPostResource {
    @GetMapping("/{id}")
    public EssayPost getPost(@PathVariable long id){
        return null;
    }

    @PostMapping
    public EssayPost addPost(EssayPostDto essayPostDto){
        return null;
    }

    @PutMapping("/{id}")
    public EssayPost updatePost(EssayPostDto essayPostDto){
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePost(){

    }
}
