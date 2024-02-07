package com.jason.tics.api.translation.feign;

import com.jason.tics.api.translation.bo.TranslationResult;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@Component
@FeignClient(value = "tics-translation")
public interface TranslationFeignClient {
    @GetMapping("/translation/{query}")
    ServerResponseEntity<TranslationResult> translate(@PathVariable("query") String query,
                                                          @Length(max = 1000)
                           @RequestParam(value = "sentence", required = false) String sentence);
}
