package com.jason.tics.dictionary.resource;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.security.annotation.SortableEntity;
import com.jason.tics.dictionary.domain.extension.StageWord;
import com.jason.tics.dictionary.domain.extension.Stage;
import com.jason.tics.dictionary.repository.StageRepository;
import com.jason.tics.dictionary.repository.StageWordRepository;
import com.jason.tics.dictionary.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/dictionary")
public class StageResource {
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private StageWordRepository stageWordRepository;
    @Autowired
    private StageService stageService;

    @GetMapping("/stage")
    public ServerResponseEntity<List<Stage>>  listStages(){
        return ServerResponseEntity.success(stageRepository.findAll());
    }

    @GetMapping("/stage/word")
    public ServerResponseEntity<Page<StageWord>> listStage(@SortableEntity(entity = StageWord.class)Pageable pageable){
        return ServerResponseEntity.success(stageWordRepository.findAll(pageable));
    }

    @PostMapping("/stage")
    public ServerResponseEntity<Stage> addStage(@Validated @RequestBody Stage stage){
        return ServerResponseEntity.success(stage);
    }

    @DeleteMapping("/stage/{name}")
    public ServerResponseEntity<Stage> deleteStage(@PathVariable String name){
        stageRepository.deleteById(name);
        return ServerResponseEntity.success();
    }

    @PutMapping("/stage/{name}")
    public ServerResponseEntity<Stage> updateStage(@RequestBody @Validated Stage stage, @PathVariable String name){
        stage.setStageName(name);
        return ServerResponseEntity.success(stageRepository.save(stage));
    }

    @PostMapping("/stage/word")
    public ServerResponseEntity<List<StageWord>> addStageWord(@RequestParam String word, @RequestParam List<String> stageNames){
        return ServerResponseEntity.success(stageService.addStageWord(stageNames, word));
    }

    @DeleteMapping("/stage/{name}/{word}")
    public ServerResponseEntity<Void> deleteStageWord(@PathVariable String name, @PathVariable String word){
        stageWordRepository.deleteById(new StageWord(name, word));
        return ServerResponseEntity.success();
    }
}
