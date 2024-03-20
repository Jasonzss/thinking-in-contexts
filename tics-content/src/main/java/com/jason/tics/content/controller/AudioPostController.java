package com.jason.tics.content.controller;

import com.jason.tics.content.domain.AudioText;
import com.jason.tics.content.domain.dto.AudioPostDto;
import com.jason.tics.content.domain.AudioPost;
import com.jason.tics.content.service.AudioPostService;
import com.jason.tics.content.service.AudioTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RequestMapping("content/audio")
@Controller
public class AudioPostController {
    @Autowired
    private AudioPostService audioPostService;
    @Autowired
    private AudioTextService audioTextService;

    @GetMapping("/{id}")
    public AudioPost getAudio(@PathVariable String id){
        return audioPostService.getAudioPost(id);
    }

    @PostMapping
    public AudioPost addAudio(@Validated @RequestBody AudioPostDto audioPostDto){
        long uid = 0L;
        return audioPostService.addAudioPost(audioPostDto, uid);
    }

    @PutMapping("/{id}/title")
    public AudioPost updateAudioTitle(@PathVariable String id,@RequestParam String title){
        return audioPostService.updateTitle(id, title);
    }

    @PutMapping("/{id}/introduction")
    public AudioPost updateAudioIntroduction(@PathVariable String id,@RequestParam String introduction){
        return audioPostService.updateIntroduction(id, introduction);
    }

    @PutMapping("/{id}/cover")
    public AudioPost updateAudioCover(@PathVariable String id,@RequestParam String cover){
        return audioPostService.updateCoverImage(id, cover);
    }

    @PutMapping("/{id}")
    public AudioPost updateAudio(@PathVariable String id,@RequestParam String audio){
        return audioPostService.updateAudio(id, audio);
    }

    @DeleteMapping("/{id}")
    public void deleteAudio(@PathVariable String id){
        audioPostService.deleteAudioPost(id);
    }

    // audioText相关

    @GetMapping("/{id}/text/{tid}")
    public AudioText getAudioText(@PathVariable long tid){
        return audioTextService.getAudioText(tid);
    }

    @DeleteMapping("/{id}/text/{tid}")
    public void deleteAudioText(@PathVariable long tid){
        audioTextService.deleteAudioText(tid);
    }

    @PostMapping("/{id}/text")
    public AudioText addAudioText(@PathVariable String id, String text){
        return audioTextService.addAudioText(id, text);
    }

    @PutMapping("/{id}/text/{tid}")
    public AudioText updateAudioText(@PathVariable String tid, String text){
        return audioTextService.updateAudioText(tid, text);
    }
}
