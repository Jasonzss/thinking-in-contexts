package com.jason.tics.dictionary.resource;

import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.jpa.utils.ResourceUtil;
import com.jason.tics.dictionary.domain.web.WebDictionary;
import com.jason.tics.dictionary.domain.web.WebTranslation;
import com.jason.tics.dictionary.repository.WebDictionaryRepository;
import com.jason.tics.dictionary.repository.WebTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/dictionary")
public class WebDictionaryResource {
    @Autowired
    private WebTranslationRepository webTranslationRepository;
    @Autowired
    private WebDictionaryRepository webDictionaryRepository;

    @GetMapping("/web/{theme}")
    public ServerResponseEntity<WebDictionary> getWebDictionary(@PathVariable String theme){
        return webDictionaryRepository.findById(theme).map(ServerResponseEntity::success).orElseGet(
                () -> ServerResponseEntity.fail(ExceptionResponseEnum.RESOURCE_NOT_FOUND));
    }

    @PostMapping("/web")
    public ServerResponseEntity<WebDictionary> addWebDictionary(@Validated @RequestBody WebDictionary webDictionary){
        return ServerResponseEntity.success(webDictionaryRepository.save(webDictionary));
    }

    @DeleteMapping("/web/{theme}")
    public ServerResponseEntity<Void> deleteWebDictionary(@PathVariable String theme){
        webDictionaryRepository.deleteById(theme);
        return ServerResponseEntity.success();
    }

    @PutMapping("/web/{theme}")
    public ServerResponseEntity<WebDictionary> updateWebDictionary(@Validated @RequestBody WebDictionary webDictionary){
        return ServerResponseEntity.success(webDictionaryRepository.save(webDictionary));
    }

    @GetMapping("/web/translation/{query}")
    public ServerResponseEntity<List<WebTranslation>> getWebTranslation(String query){
        return ServerResponseEntity.success(webTranslationRepository.getWebTranslationByTargetEquals(query));
    }

    @PostMapping("/web/translation")
    public ServerResponseEntity<WebTranslation> addWebTranslation(String theme, String target,
                                                                  String translation,@Uid long uid){
        return ServerResponseEntity.success(webTranslationRepository
                .save(new WebTranslation(target, theme, uid, translation)));
    }

    @DeleteMapping("/web/translation/{id}")
    public ServerResponseEntity<Void> deleteWebTranslation(@PathVariable long id){
        webTranslationRepository.deleteById(id);
        return ServerResponseEntity.success();
    }

    @PutMapping("/web/translation")
    public ServerResponseEntity<WebTranslation> updateWebTranslation(@Validated @RequestBody WebTranslation webTranslation){
        return ServerResponseEntity.success(ResourceUtil
                .updateResource(webTranslation, webTranslation.getId(), webTranslationRepository));
    }
}
