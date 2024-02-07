package com.jason.tics.dictionary.feign;

import com.jason.tics.api.translation.feign.TranslationFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jason
 */
@SpringBootTest
@Slf4j
public class TranslationFeignClientTest {
    @Autowired
    private TranslationFeignClient translationFeignClient;

    @Test
    public void testTranslateWord(){
        log.info("调用feign："+translationFeignClient.translate("yes", null));
    }
}
