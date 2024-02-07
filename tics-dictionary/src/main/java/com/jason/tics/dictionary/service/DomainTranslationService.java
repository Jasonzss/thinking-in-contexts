package com.jason.tics.dictionary.service;

import com.jason.tics.dictionary.domain.DomainTranslation;

import java.util.List;

/**
 * @author Jason
 */
public interface DomainTranslationService {
    /**
     * 根据用户感兴趣的领域查出对应的领域翻译词汇
     *
     * @param query 查询语句
     * @return 领域翻译
     */
    List<DomainTranslation> searchDomainTranslation(String query, Long uid);
}
