package com.jason.tics.translation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 * @since 2023/09/09 - 14:38
 */
@RequestMapping(value = "/translation")
@Controller
public class CommonTranslationController {
    @GetMapping("/result")
    @ResponseBody
    public String translate(@RequestParam String word, @RequestParam String lang){
        return word;
    }

    @GetMapping("/result")
    @ResponseBody
    public String translateInContexts(@RequestParam String word, @RequestParam String sentence,@RequestParam String lang){
        return word;
    }
}
