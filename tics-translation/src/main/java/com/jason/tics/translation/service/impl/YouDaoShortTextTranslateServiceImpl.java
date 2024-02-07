package com.jason.tics.translation.service.impl;

import com.google.gson.Gson;
import com.jason.tics.api.translation.bo.TranslationResult;
import com.jason.tics.translation.domain.youdao.YouDaoTextTranslationResponse;
import com.jason.tics.translation.repository.YoudaoRepository;
import com.jason.tics.translation.service.ShortTextTranslateService;
import com.jason.tics.translation.utils.AuthV3Util;
import com.jason.tics.translation.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason
 */
@Service
@Slf4j
public class YouDaoShortTextTranslateServiceImpl implements ShortTextTranslateService {

    private static final String APP_KEY = "24cf8a3a7e93d541";     // 您的应用ID
    private static final String APP_SECRET = "xXUS41JBJ97ixiUWNGNpw6epvQDG4J8L";  // 您的应用密钥
    private static final String YOUDAO_URL = "https://openapi.youdao.com/api";
    private static final String EXPECT_CONTENT_TYPE = "application/json";

    public static final String DEFAULT_TO_LANGUAGE = "zh-CHS";
    public static final String DEFAULT_FROM_LANGUAGE = "en";
    private final Gson gson = new Gson();

    @Autowired
    private YoudaoRepository youdaoRepository;

    @Override
    public TranslationResult translate(String content) {
        return doTranslate(content).getWordTranslationResult();
    }

    @Override
    public TranslationResult translateInContexts(String word, String sentence) {
        //根据句子推断单词释义
        return doTranslate(sentence).getWordTranslationResult();
    }

    public YouDaoTextTranslationResponse doTranslate(String q) {
        q = q.trim();

        //先查看数据库中是否存在翻译
        YouDaoTextTranslationResponse r;
        if ((r = translateFromDb(q)) != null){
            log.info("find 【"+q+"】 in database");
            return r;
        }

        // 添加请求参数
        Map<String, String[]> params = createRequestParams(q);
        // 添加鉴权相关参数
        try {
            AuthV3Util.addAuthParams(APP_KEY, APP_SECRET, params);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 请求api服务
        log.info("try youdao translation : "+q);
        byte[] result = HttpUtil.doPost(YOUDAO_URL, null, params, EXPECT_CONTENT_TYPE);

        String s = new String(result, StandardCharsets.UTF_8);
        log.info("youdao translate success : "+s);

        r = gson.fromJson(s, YouDaoTextTranslationResponse.class);
        youdaoRepository.save(r);
        return r;
    }

    private YouDaoTextTranslationResponse translateFromDb(String q){
        return youdaoRepository.findById(q).orElse(null);
    }

    private static Map<String, String[]> createRequestParams(String q) {
        /*
         * note: 将下列变量替换为需要请求的参数
         * 取值参考文档: https://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html
         */

        return new HashMap<String, String[]>() {{
            put("q", new String[]{q});
            put("to", new String[]{DEFAULT_TO_LANGUAGE});
            put("from", new String[]{DEFAULT_FROM_LANGUAGE});
        }};
    }
}
