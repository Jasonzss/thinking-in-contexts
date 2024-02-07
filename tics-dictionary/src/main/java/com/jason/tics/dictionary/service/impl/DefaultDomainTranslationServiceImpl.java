package com.jason.tics.dictionary.service.impl;

import com.jason.tics.dictionary.domain.DomainTranslation;
import com.jason.tics.dictionary.repository.DomainTranslationRepository;
import com.jason.tics.dictionary.service.DomainTranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason
 */
@Service
public class DefaultDomainTranslationServiceImpl implements DomainTranslationService {
    @Autowired
    private DomainTranslationRepository domainTranslationRepository;

    @Override
    public List<DomainTranslation> searchDomainTranslation(String query, Long uid) {
        return filterDomainTranslation(domainTranslationRepository.findAllByTargetContaining(query), query);
    }

    private List<DomainTranslation> filterDomainTranslation(List<DomainTranslation> domainTranslations, String query){
        List<DomainTranslation> d = new ArrayList<>();

        for (DomainTranslation domainTranslation : domainTranslations) {
            String target = domainTranslation.getTarget();
            if (target.startsWith(query) || target.endsWith(query) || target.contains(" "+query+" ")){
                d.add(domainTranslation);
            }
        }

        return d;
    }
}
