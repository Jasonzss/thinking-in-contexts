package com.jason.tics.exercise.resource;

import com.jason.tics.common.security.annotation.SortableEntity;
import com.jason.tics.exercise.domain.BaseQuestion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.math.Fraction;
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
@RequestMapping("/exercise/question")
@Api("习题Controller")
public class QuestionResource {

    @GetMapping("/{id}")
    public BaseQuestion getBaseQuestion(@PathVariable String id){
        return null;
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
    public Page<BaseQuestion> listBaseQuestion(@PageableDefault(size = 20)
                                           @SortableEntity(entity = BaseQuestion.class) Pageable pageable){
        return null;
    }

    @GetMapping("/{id}/answer")
    public List<String> getBaseQuestionAnswer(@PathVariable String id){
        return null;
    }

    @GetMapping("/{id}/correcting")
    public Fraction getBaseQuestionCorrecting(@PathVariable String id, @RequestParam String... userAnswers){
        return null;
    }

    @ApiImplicitParam(name = "question", value = "问题实体", required = true, dataType = "BaseQuestion的子类")
    @PutMapping("/{id}")
    public BaseQuestion updateBaseQuestion(@Validated BaseQuestion question){
        return null;
    }

    @PostMapping
    public BaseQuestion addBaseQuestion(@Validated BaseQuestion question){
        return null;
    }

    @DeleteMapping("{id}")
    public void deleteBaseQuestion(@PathVariable String id){

    }
}
