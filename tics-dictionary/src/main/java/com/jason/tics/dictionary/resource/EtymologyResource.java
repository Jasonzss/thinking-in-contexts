package com.jason.tics.dictionary.resource;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.dictionary.domain.extension.Etymology;
import com.jason.tics.dictionary.repository.EtymologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/dictionary/etymology")
public class EtymologyResource {
    @Autowired
    private EtymologyRepository etymologyRepository;

    @GetMapping("/{word}")
    public ServerResponseEntity<Etymology> getEtymology(@PathVariable String word){
        return ServerResponseEntity.success(etymologyRepository.getById(word));
    }

    @PostMapping
    public ServerResponseEntity<Etymology> addEtymology(@RequestBody Etymology etymology){
        return ServerResponseEntity.success(etymologyRepository.save(etymology));
    }

    @DeleteMapping("/{word}")
    public ServerResponseEntity<Void> deleteEtymology(@PathVariable String word){
        etymologyRepository.deleteById(word);
        return ServerResponseEntity.success();
    }

    @PutMapping("/{word}")
    public ServerResponseEntity<Etymology> updateEtymology(@RequestBody Etymology etymology){
        return ServerResponseEntity.success(etymologyRepository.save(etymology));
    }
}
