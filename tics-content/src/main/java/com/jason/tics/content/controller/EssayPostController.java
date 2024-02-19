package com.jason.tics.content.controller;

import com.jason.tics.content.domain.EssayPost;
import com.jason.tics.content.domain.dto.EssayPostDto;
import com.jason.tics.content.service.EssayPostService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RequestMapping("essay")
public class EssayPostController {
    private EssayPostService essayPostService;

    @GetMapping("/{id}")
    public EssayPost getEssay(@PathVariable String id){
        return essayPostService.getEssay(id);
    }

    @PostMapping
    public EssayPost addEssay(@Validated @RequestBody EssayPostDto essayPostDto){
        return essayPostService.addEssay(essayPostDto);
    }

    @PutMapping("/{id}")
    public EssayPost updateEssay(@PathVariable String id, String essay){
        EssayPost essayPost = new EssayPost();
        essayPost.setEssayId(id);
        essayPost.setEssay(essay);
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
