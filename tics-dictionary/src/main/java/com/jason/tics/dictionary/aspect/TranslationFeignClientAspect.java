package com.jason.tics.dictionary.aspect;

import cn.hutool.core.collection.CollectionUtil;
import com.google.gson.Gson;
import com.jason.tics.api.translation.bo.TranslationResult;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.dictionary.domain.extension.Variant;
import com.jason.tics.dictionary.domain.web.WebTranslation;
import com.jason.tics.dictionary.repository.VariantRepository;
import com.jason.tics.dictionary.service.StageService;
import com.jason.tics.dictionary.service.WebDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason
 */
@Aspect
@Component
@Slf4j
public class TranslationFeignClientAspect {
    @Autowired
    private StageService stageService;
    @Resource(name = "officialWebTranslationServiceImpl")
    private WebDictionaryService webDictionaryService;
    @Autowired
    private VariantRepository variantRepository;
    private final Gson gson = new Gson();

    @Around("execution(* com.jason.tics.api.translation.feign.TranslationFeignClient.translate(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 调用翻译方法得到翻译结果
        ServerResponseEntity<TranslationResult> result = (ServerResponseEntity<TranslationResult>) joinPoint.proceed();
        TranslationResult data = result.getData();
        // 解析翻译结果并储存
        log.info("store translation result:"+data);

        if (CollectionUtil.isNotEmpty(data.getStages())){
            stageService.addStageWord(data.getStages(), data.getQuery());
        }

        if (CollectionUtil.isNotEmpty(data.getWordForm())) {
            List<Variant> variants = new ArrayList<>();
            data.getWordForm().forEach((name, value) -> variants.add(new Variant(value, data.getQuery(), name)));
            variantRepository.saveAll(variants);
        }

        if (CollectionUtil.isNotEmpty(data.getWeb())){
            data.getWeb().forEach((k, v)->{
                WebTranslation webTranslation = new WebTranslation();
                webTranslation.setTarget(k);
                webTranslation.setTranslation(gson.toJson(v));
                webDictionaryService.addWebTranslation(webTranslation);
            });
        }

        return result;
    }
}
