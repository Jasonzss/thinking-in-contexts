package com.jason.tics.exercise.resource;

import com.jason.tics.common.security.annotation.SortableEntity;
import com.jason.tics.exercise.domain.question.FillBlankQuestion;
import com.jason.tics.exercise.repository.FillBlankQuestionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.math.Fraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jason
 */
@Controller
@RequestMapping("/exercise/question/fil")
@Api("RESTful填空题")
public class FillBlankQuestionResource {
    @Autowired
    private FillBlankQuestionRepository fillBlankQuestionRepository;

    @GetMapping("/{id}")
    public FillBlankQuestion getFillBlankQuestion(@PathVariable String id){
        return fillBlankQuestionRepository.getById(id);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询所有题目", notes = "传入page、size、sort以进行分页排序查询，顺序加入asc，逆序desc")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页的页数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "size", value = "每页所包含的数据量", required = true, dataType = "int"),
            @ApiImplicitParam(name = "sort", value = "用于排序的字段", dataType = "int"),
            @ApiImplicitParam(name = "asc", value = "顺序查询"),
            @ApiImplicitParam(name = "desc", value = "倒序查询")
    })
    public Page<FillBlankQuestion> listBaseQuestion(@PageableDefault(size = 20)
                                                 @SortableEntity(entity = FillBlankQuestion.class) Pageable pageable){
        return fillBlankQuestionRepository.findAll(pageable);
    }

    @GetMapping("/{id}/answer")
    public List<String> getBaseQuestionAnswer(@PathVariable String id){
        return fillBlankQuestionRepository.getById(id).getAnswers();
    }

    @GetMapping("/{id}/correcting")
    public Fraction getFillBlankQuestionCorrecting(@PathVariable String id, @RequestParam String... userAnswers){
        return fillBlankQuestionRepository.getById(id).answer(userAnswers);
    }

    @ApiImplicitParam(name = "question", value = "填空题实体", required = true, dataType = "FillBlankQuestion")
    @PutMapping("/{id}")
    public FillBlankQuestion updateFillBlankQuestion(@Validated FillBlankQuestion question){
        return fillBlankQuestionRepository.save(question);
    }

    @PostMapping
    public FillBlankQuestion addFillBlankQuestion(@Validated FillBlankQuestion question){
        return fillBlankQuestionRepository.save(question);
    }

    @DeleteMapping("{id}")
    public void deleteFillBlankQuestion(@PathVariable String id){
        fillBlankQuestionRepository.deleteById(id);
    }
}
