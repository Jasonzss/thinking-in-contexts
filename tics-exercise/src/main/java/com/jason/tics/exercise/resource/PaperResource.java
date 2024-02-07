package com.jason.tics.exercise.resource;

import com.jason.tics.exercise.domain.Paper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jason
 */
@RequestMapping("/exercise/paper")
@Controller
public class PaperResource {
    public Paper getPaper(long paperId){
        return null;
    }

    public Paper getRecommendedPaper(long uid, int questionNum, String... questionType){
        return null;
    }

    public void addPaper(){

    }

    public void deletePaper(){

    }

    public void updatePaper(){

    }
}
