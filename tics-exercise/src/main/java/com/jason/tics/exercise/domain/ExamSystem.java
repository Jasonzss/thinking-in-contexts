package com.jason.tics.exercise.domain;

import com.jason.tics.exercise.domain.dto.PaperDto;
import org.springframework.data.domain.Page;

/**
 * @author Jason
 */
public class ExamSystem {
    //考试功能

    public Paper startExam(long paperId, long uid){
        return null;
    }

    public void pauseExam(long answerSheetId){

    }

    public void continueExam(long answerSheetId){

    }

    public AnswerSheet finishExam(long answerSheetId){
        return null;
    }

    public PaperAnswer saveAnswer(PaperAnswer answer){
        return null;
    }

    //后台试卷管理

    public Paper savePaper(PaperDto paperDto){
        return null;
    }

    public void deletePaper(long paperId){

    }

    public Paper getPaper(long paperId){
        return null;
    }

    public Page<Paper> listPager(){
        return null;
    }
}
