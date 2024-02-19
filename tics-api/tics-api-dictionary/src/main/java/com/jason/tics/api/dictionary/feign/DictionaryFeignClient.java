package com.jason.tics.api.dictionary.feign;

import com.jason.tics.api.dictionary.bo.ConfusedDictionaryBo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jason
 */
@Component
@FeignClient(value = "tics-dictionary")
public interface DictionaryFeignClient {
    @GetMapping("/dictionary/{word}/confusion")
    ConfusedDictionaryBo getConfusedDictionary(String word);
}
