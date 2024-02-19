package com.jason.tics.content.controller;

import com.jason.tics.comment.meme.CommentMeme;
import com.jason.tics.comment.meme.Meme;
import com.jason.tics.content.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * meme的后台管理
 *
 * @author Jason
 */
@Controller
public class MemeController {
    @Autowired
    private CommentService commentService;

    // meme管理方面

    @GetMapping("/meme")
    public List<Meme> listMemes(@RequestParam(required = false) int[] id){
        if(id == null){
            return commentService.listMemes();
        }else {
            return commentService.listMemes(id);
        }
    }

    @PostMapping("/meme")
    public Meme addMeme(@RequestParam String name,@RequestParam String image){
        return commentService.saveMeme(name, image);
    }

    @DeleteMapping("/meme")
    public void deleteMeme(@RequestParam int[] id){
        commentService.deleteMemes(id);
    }

    @PutMapping("/meme/{id}")
    public void updateMeme(@PathVariable int id, @RequestParam(required = false) String name,
                           @RequestParam(required = false) String image){
        Meme meme = new Meme();
        meme.setMemeId(id);
        meme.setMemeName(name);
        meme.setMemeImage(image);
        commentService.updateMeme(meme);
    }

    // meme 正常使用方面

    @GetMapping("/*/{id}/comment/{cid}/meme")
    public List<CommentMeme> listCommentMemes(@RequestParam long cid){
        return commentService.listCommentMemes(cid);
    }

    @PostMapping("/*/{id}/comment/{cid}/meme")
    public void likeCommentMeme(@RequestParam long cid){
        long uid = 1;
        commentService.likeCommentMeme(cid, uid);
    }

    @DeleteMapping("/*/{id}/comment/{cid}/meme")
    public void unlikeCommentMeme(@RequestParam long cid){
        long uid = 1;
        commentService.unlikeCommentMeme(cid, uid);
    }

}
