package com.jason.tics.exercise.resource;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.exercise.domain.exercise.WordChoiceExerciseInfo;
import com.jason.tics.exercise.domain.exercise.WordTranslationChoiceExercise;
import com.jason.tics.exercise.domain.exercise.WordTranslationChoiceExerciseSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RequestMapping("/exercise/word")
@Controller
public class WordExerciseResource {
    @Autowired
    private WordTranslationChoiceExerciseSystem wordTranslationChoiceExerciseSystem;

    @GetMapping
    public ServerResponseEntity<WordTranslationChoiceExercise> getNext(@Uid long uid){
        return ServerResponseEntity.success(wordTranslationChoiceExerciseSystem.nextWordExercise(uid));
    }

    @PostMapping("/{word}")
    public String pointWord(@Uid long uid, @PathVariable String word,@RequestParam String answer){
        if (wordTranslationChoiceExerciseSystem.pointWord(uid,word,answer)) {
            return "redirect:/exercise/word";
        }else{
            return "redirect:/dictionary/"+word;
        }
    }

    @DeleteMapping
    public ServerResponseEntity<WordChoiceExerciseInfo> endExercise(@Uid long uid){
        return ServerResponseEntity.success(wordTranslationChoiceExerciseSystem.endExercise(uid));
    }
}
