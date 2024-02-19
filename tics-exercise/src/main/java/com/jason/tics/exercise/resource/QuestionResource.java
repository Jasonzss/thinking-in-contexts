package com.jason.tics.exercise.resource;

import com.jason.tics.exercise.domain.QuestionType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jason
 */
@Controller
@RequestMapping("/exercise/question")
public class QuestionResource {
    @GetMapping("/{id}")
    public String getQuestion(String id){
        return "redirect:/exercise/question/"+id.substring(0, QuestionType.PREFIX_LENGTH)+"/"+id;
    }

}
