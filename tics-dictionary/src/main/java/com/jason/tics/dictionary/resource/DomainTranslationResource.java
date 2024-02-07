package com.jason.tics.dictionary.resource;

import com.jason.tics.common.jpa.utils.ResourceUtil;
import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.dictionary.domain.DomainTranslation;
import com.jason.tics.dictionary.repository.DomainTranslationRepository;
import com.jason.tics.dictionary.service.DomainTranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/dictionary")
public class DomainTranslationResource {
    @Autowired
    private DomainTranslationService domainTranslationService;
    @Autowired
    private DomainTranslationRepository domainTranslationRepository;

    @GetMapping("/{query}/domain")
    public ServerResponseEntity<List<DomainTranslation>> searchDomainTranslation(@PathVariable String query, @Uid long uid){
        return ServerResponseEntity.success(domainTranslationService.searchDomainTranslation(query, uid));
    }

    @GetMapping("/domain/{id}")
    public ServerResponseEntity<DomainTranslation> getDomainTranslation(@PathVariable long id){
        return ServerResponseEntity.success(domainTranslationRepository.getById(id));
    }

    @PostMapping("/domain")
    public ServerResponseEntity<DomainTranslation> addDomainTranslation(@RequestBody DomainTranslation domainTranslation){
        return ServerResponseEntity.success(domainTranslationRepository.save(domainTranslation));
    }

    @DeleteMapping("/domain/{id}")
    public ServerResponseEntity<Void> deleteDomainTranslation(@PathVariable long id){
        domainTranslationRepository.deleteById(id);
        return ServerResponseEntity.success();
    }

    @PutMapping("/domain/{id}")
    public ServerResponseEntity<DomainTranslation> updateDomainTranslation(DomainTranslation domainTranslation){
        return ServerResponseEntity.success(ResourceUtil.updateResource(domainTranslation,
                domainTranslation.getId(), domainTranslationRepository));
    }
}