package com.jason.tics.dictionary.resource;

import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.dictionary.domain.vo.DictionaryVo;
import com.jason.tics.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController
public class DictionaryResource {
    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/dictionary/{query}")
    public DictionaryVo get(@PathVariable String query, @Uid long uid){
        return dictionaryService.getDictionary(query, uid);
    }
}
