package com.jason.tics.exercise.resource;

import com.jason.tics.common.jpa.utils.JpaCrudUtil;
import com.jason.tics.common.security.annotation.SortableEntity;
import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.exercise.domain.exam.Paper;
import com.jason.tics.exercise.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RequestMapping("/exercise/paper")
@RestController
public class PaperResource {
    @Autowired
    private PaperRepository repository;

    @GetMapping("/{paperId}")
    public Paper getPaper(@PathVariable long paperId){
        return repository.getById(paperId);
    }

    @GetMapping("/{paperId}/page")
    public Page<Paper> listPager(@SortableEntity(entity = Pageable.class) Pageable pageable){
        return repository.findAll(pageable);
    }

    @PostMapping
    public Paper addPaper(@RequestBody @Validated Paper paper, @Uid long uid){
        paper.setId(uid);
        return repository.save(paper);
    }

    @DeleteMapping("/{paperId}")
    public void deletePaper(@PathVariable long paperId){
        repository.deleteById(paperId);
    }

    @PutMapping("/{paperId}")
    public void updatePaper(@PathVariable long paperId, Paper paper){
        JpaCrudUtil.updateResource(paper, paperId, repository);
    }
}
