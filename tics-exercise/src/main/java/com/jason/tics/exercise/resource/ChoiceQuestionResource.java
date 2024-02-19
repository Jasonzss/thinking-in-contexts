package com.jason.tics.exercise.resource;

import com.jason.tics.common.security.annotation.SortableEntity;
import com.jason.tics.exercise.domain.question.ChoiceQuestion;
import com.jason.tics.exercise.repository.ChoiceQuestionRepository;
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
@RequestMapping("/exercise/question/cho")
@Api("RESTful选择题")
public class ChoiceQuestionResource {
    @Autowired
    private ChoiceQuestionRepository choiceQuestionRepository;

    @GetMapping("/{id}")
    public ChoiceQuestion getChoiceQuestion(@PathVariable String id){
        return choiceQuestionRepository.getById(id);
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
    public Page<ChoiceQuestion> listBaseQuestion(@PageableDefault(size = 20)
                                           @SortableEntity(entity = ChoiceQuestion.class) Pageable pageable){
        return choiceQuestionRepository.findAll(pageable);
    }

    @GetMapping("/{id}/answer")
    public List<String> getBaseQuestionAnswer(@PathVariable String id){
        return choiceQuestionRepository.getById(id).getAnswers();
    }

    @GetMapping("/{id}/correcting")
    public Fraction getChoiceQuestionCorrecting(@PathVariable String id, @RequestParam String... userAnswers){
        return choiceQuestionRepository.getById(id).answer(userAnswers);
    }

    @ApiImplicitParam(name = "question", value = "选择题实体", required = true, dataType = "ChoiceQuestion")
    @PutMapping("/{id}")
    public ChoiceQuestion updateChoiceQuestion(@Validated ChoiceQuestion question){
        return choiceQuestionRepository.save(question);
    }

    @PostMapping
    public ChoiceQuestion addChoiceQuestion(@Validated ChoiceQuestion question){
        return choiceQuestionRepository.save(question);
    }

    @DeleteMapping("{id}")
    public void deleteChoiceQuestion(@PathVariable String id){
        choiceQuestionRepository.deleteById(id);
    }
}
