package com.jason.tics.content.controller;

import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.content.domain.VideoPost;
import com.jason.tics.content.domain.VideoSubtitle;
import com.jason.tics.content.domain.dto.VideoPostDto;
import com.jason.tics.content.service.VideoPostService;
import com.jason.tics.content.service.VideoSubtitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RequestMapping("content/video")
@Controller
public class VideoPostController {
    @Autowired
    private VideoPostService videoPostService;
    @Autowired
    private VideoSubtitleService videoSubtitleService;

    @GetMapping("/{id}")
    public VideoPost getVideo(@PathVariable String id){
        return videoPostService.getVideo(id);
    }

    @PostMapping
    public VideoPost addVideo(@RequestBody @Validated VideoPostDto videoPostDto, @Uid long uid){
        return videoPostService.addVideo(videoPostDto, uid);
    }

    @PutMapping("/{id}")
    public VideoPost updateVideo(@PathVariable String id,@RequestParam String video){
        return videoPostService.updateVideo(id, video);
    }

    @PutMapping("/{id}/title")
    public VideoPost updateVideoTitle(@PathVariable String id,@RequestParam String title){
        return videoPostService.updateTitle(id, title);
    }

    @PutMapping("/{id}/introduction")
    public VideoPost updateVideoIntroduction(@PathVariable String id,@RequestParam String introduction){
        return videoPostService.updateIntroduction(id, introduction);
    }

    @PutMapping("/{id}/cover")
    public VideoPost updateVideoCoverImage(@PathVariable String id,@RequestParam String cover){
        return videoPostService.updateCoverImage(id, cover);
    }

    @DeleteMapping("/{id}")
    public void deleteVideo(@PathVariable String id){
        videoPostService.deleteVideo(id);
    }


    // videoSubtitle相关

    @GetMapping("/{id}/subtitle/{sid}")
    public VideoSubtitle getVideoSubtitle(@PathVariable long sid){
        return videoSubtitleService.getVideoSubtitle(sid);
    }

    @DeleteMapping("/{id}/subtitle/{sid}")
    public void deleteVideoSubtitle(@PathVariable long sid){
        videoSubtitleService.deleteVideoSubtitle(sid);
    }

    @PostMapping("/{id}/subtitle")
    public VideoSubtitle addVideoSubtitle(@PathVariable String id, String subtitle){
        return videoSubtitleService.addVideoSubtitle(id, subtitle);
    }

    @PutMapping("/{id}/subtitle/{sid}")
    public VideoSubtitle updateVideoSubtitle(@PathVariable Long sid, String subtitle){
        return videoSubtitleService.updateVideoSubtitle(sid, subtitle);
    }
}
