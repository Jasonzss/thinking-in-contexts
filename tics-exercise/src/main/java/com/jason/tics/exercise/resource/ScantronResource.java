package com.jason.tics.exercise.resource;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.exercise.domain.exam.PaperAnswer;
import com.jason.tics.exercise.domain.exam.PreparationExamSystem;
import com.jason.tics.exercise.domain.exam.Scantron;
import com.jason.tics.exercise.repository.PaperAnswerRepository;
import com.jason.tics.exercise.repository.ScantronRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@Controller
@RequestMapping("/exercise/scantron")
@Api("RESTful答题卡")
public class ScantronResource {
    @Autowired
    private ScantronRepository repository;
    @Autowired
    private PaperAnswerRepository paperAnswerRepository;

    @Autowired
    private PreparationExamSystem examSystem;

    @GetMapping("/{id}")
    public ServerResponseEntity<Scantron> getScanTron(@PathVariable long id){
        return ServerResponseEntity.success(repository.getById(id));
    }

    @DeleteMapping("/{id}")
    public ServerResponseEntity<Void> deleteScantron(@PathVariable long id){
        repository.deleteById(id);
        return ServerResponseEntity.success();
    }

    @ApiOperation(value = "开始考试")
    @PostMapping("/exam")
    public ServerResponseEntity<Scantron> startExam(@RequestParam long paperId, @Uid long uid){
        return ServerResponseEntity.success(examSystem.startExam(paperId, uid));
    }

    @ApiOperation(value = "暂停/继续 考试")
    @PutMapping("/exam/{id}")
    public ServerResponseEntity<Scantron> continueExam(@PathVariable long id, @RequestParam boolean continueExam){
        if (continueExam) {
            return ServerResponseEntity.success(examSystem.continueExam(id));
        }else {
            examSystem.pauseExam(id);
            return ServerResponseEntity.success("已暂停考试");
        }
    }

    @ApiOperation(value = "结束考试")
    @DeleteMapping("/exam/{id}")
    public ServerResponseEntity<Scantron> endExam(@PathVariable long id){
        return ServerResponseEntity.success(examSystem.finishExam(id));
    }

    @ApiOperation(value = "填写考题")
    @PostMapping("/{id}/question/{index}")
    public ServerResponseEntity<PaperAnswer> answer(@PathVariable long id,
                                                    @PathVariable int index,
                                                    @RequestParam String... userAnswer){
        PaperAnswer paperAnswer = new PaperAnswer();
        paperAnswer.setScantron(new Scantron(id));
        paperAnswer.setIdx(index);
        paperAnswer.setAnswer(userAnswer);
        return ServerResponseEntity.success(examSystem.saveAnswer(paperAnswer));
    }

    @ApiOperation(value = "修改考题")
    @PutMapping("/{id}/question/{index}")
    public ServerResponseEntity<PaperAnswer> modifyAnswer(@PathVariable long id,
                                                          @PathVariable int index,
                                                          @RequestParam String... userAnswer){
        PaperAnswer paperAnswer = new PaperAnswer();
        paperAnswer.setScantron(new Scantron(id));
        paperAnswer.setIdx(index);
        paperAnswer.setAnswer(userAnswer);
        return ServerResponseEntity.success(examSystem.saveAnswer(paperAnswer));
    }

    @ApiOperation(value = "查看考试答题")
    @GetMapping("/{id}/question/{index}")
    public ServerResponseEntity<PaperAnswer> getAnswer(@PathVariable long id,
                                                    @PathVariable int index){
        return ServerResponseEntity.success(paperAnswerRepository
                .findByScantronIdAndIdx(id, index).orElse(null));
    }
}
