package com.jason.tics.translation.feign;

import com.jason.tics.api.translation.bo.TranslationResult;
import com.jason.tics.api.translation.feign.TranslationFeignClient;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.translation.service.ShortTextTranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 * @since 2023/09/09 - 14:38
 */
@RestController
public class TranslationController implements TranslationFeignClient {
    @Autowired
    private ShortTextTranslateService service;

    @Override
    public ServerResponseEntity<TranslationResult> translate(String query, String sentence) {
        return sentence == null ? ServerResponseEntity.success(service.translate(query)) :
                ServerResponseEntity.success(service.translateInContexts(query, sentence));
    }
}
