package com.jason.tics.translation.service;

import com.jason.tics.translation.TranslationApplication;
import com.jason.tics.translation.service.impl.YouDaoShortTextTranslateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jason
 */
@Slf4j
@SpringBootTest(classes = TranslationApplication.class)
public class YouDaoShortTextTranslateServiceTest {
    @Autowired
    private YouDaoShortTextTranslateServiceImpl service;

    @Test
    public void testTranslate(){
        log.info(service.doTranslate("nigger").toString());
    }
}
