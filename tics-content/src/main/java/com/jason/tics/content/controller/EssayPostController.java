package com.jason.tics.content.controller;

import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.content.domain.EssayPost;
import com.jason.tics.content.domain.dto.EssayPostDto;
import com.jason.tics.content.service.EssayPostService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RequestMapping("content/essay")
public class EssayPostController {
    private EssayPostService essayPostService;

    @GetMapping("/{id}")
    public EssayPost getEssay(@PathVariable String id){
        return essayPostService.getEssay(id);
    }

    @PostMapping
    public EssayPost addEssay(@Validated @RequestBody EssayPostDto essayPostDto, @Uid long uid){
        return essayPostService.addEssay(essayPostDto, uid);
    }

    @PutMapping("/{id}")
    public EssayPost updateEssay(@PathVariable String id, String essay){
        EssayPost essayPost = new EssayPost();
        essayPost.setEssayId(id);
        essayPost.setEssayUrl(essay);
        return essayPostService.updateEssay(essayPost);
    }

    @PutMapping("/{id}/title")
    public EssayPost updateEssayTitle(@PathVariable String id,@RequestParam String title){
        EssayPost essayPost = new EssayPost();
        essayPost.setEssayId(id);
        essayPost.setTitle(title);
        return essayPostService.updateEssay(essayPost);
    }

    @PutMapping("/{id}/cover")
    public EssayPost updateEssayCover(@PathVariable String id,@RequestParam String cover){
        return essayPostService.updateEssayCover(id, cover);
    }

    @DeleteMapping("/{id}")
    public void deleteEssay(@PathVariable String id){
        essayPostService.deleteEssay(id);
    }
}
