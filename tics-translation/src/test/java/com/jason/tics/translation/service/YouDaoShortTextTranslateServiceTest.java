package com.jason.tics.translation.service;

import com.google.gson.Gson;
import com.jason.tics.translation.TranslationApplication;
import com.jason.tics.translation.domain.youdao.YouDaoTextTranslationResponse;
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
        log.info(service.doTranslate("bug").toString());
    }

    @Test
    public void testJson() {
        String s = "{\n" +
                "  \"returnPhrase\":[\"text\"],\n" +
                "  \"query\":\"text\",\n" +
                "  \"errorCode\":\"0\",\n" +
                "  \"l\":\"en2zh-CHS\",\n" +
                "  \"tSpeakUrl\":\"https://openapi.youdao.com/ttsapi?q=%E6%96%87%E6%9C%AC&langType=zh-CHS&sign=3333D9259A372F1CF51A6F7FB1018442&salt=1705062437031&voice=4&format=mp3&appKey=24cf8a3a7e93d541&ttsVoiceStrict=false&osType=api\",\n" +
                "  \"web\":[\n" +
                "    {\"value\":[\"文本\",\"文字\",\"课文\",\"文本框\"],\"key\":\"Text\"},\n" +
                "    {\"value\":[\"文本编辑器\",\"文字编辑器\",\"文本编辑\",\"文本编辑程序\"], \"key\":\"text editor\"},\n" +
                "    {\"value\":[\"文本文件\",\"文字档\",\"程式本文档\"],\"key\":\"text file\"}\n" +
                "  ],\n" +
                "  \"requestId\":\"dff99df1-d348-4a8c-a6b9-3cb6a5166b37\",\n" +
                "  \"translation\":[\"文本\"],\n" +
                "  \"mTerminalDict\":{\n" +
                "    \"url\":\"https://m.youdao.com/m/result?lang=en&word=text\"\n" +
                "  },\n" +
                "  \"dict\":{\n" +
                "    \"url\":\"yddict://m.youdao.com/dict?le=eng&q=text\"\n" +
                "  },\n" +
                "  \"webdict\":{\n" +
                "    \"url\":\"http://mobile.youdao.com/dict?le=eng&q=text\"\n" +
                "  },\n" +
                "  \"basic\":{\n" +
                "    \"exam_type\":[\"初中\",\"高中\",\"CET4\",\"CET6\",\"考研\"],\n" +
                "    \"us-phonetic\":\"tekst\",\n" +
                "    \"phonetic\":\"tekst\",\n" +
                "    \"uk-phonetic\":\"tekst\",\n" +
                "    \"wfs\":[\n" +
                "      {\"wf\":{\"name\":\"复数\",\"value\":\"texts\"}},\n" +
                "      {\"wf\":{\"name\":\"第三人称单数\",\"value\":\"texts\"}},\n" +
                "      {\"wf\":{\"name\":\"现在分词\",\"value\":\"texting\"}},\n" +
                "      {\"wf\":{\"name\":\"过去式\",\"value\":\"texted\"}},\n" +
                "      {\"wf\":{\"name\":\"过去分词\",\"value\":\"texted\"}}\n" +
                "    ],\n" +
                "    \"uk-speech\":\"https://openapi.youdao.com/ttsapi?q=text&langType=en&sign=85B8D83429E3013FF708CCE94981E620&salt=1705062437031&voice=5&format=mp3&appKey=24cf8a3a7e93d541&ttsVoiceStrict=false&osType=api\",\n" +
                "    \"explains\":[\"n. （书、杂志等中区别于图片的）正文，文字材料；（演说等的）原文；（学习某课程必读的）课本，教科书；（供讨论或回答问题的）文本，文章；（讨论等的）题目，主题；（尤指科学或学术方面的）文献；（计算机、手机等）文档；短信；（尤指引作布道主题的）圣经经文；（尤指用于手稿的）粗体正楷\",\"v. （用手机）给……发短信\"],\n" +
                "    \"us-speech\":\"https://openapi.youdao.com/ttsapi?q=text&langType=en&sign=85B8D83429E3013FF708CCE94981E620&salt=1705062437031&voice=6&format=mp3&appKey=24cf8a3a7e93d541&ttsVoiceStrict=false&osType=api\"},\n" +
                "  \"isWord\":true,\n" +
                "  \"speakUrl\":\"https://openapi.youdao.com/ttsapi?q=text&langType=en-USA&sign=85B8D83429E3013FF708CCE94981E620&salt=1705062437031&voice=4&format=mp3&appKey=24cf8a3a7e93d541&ttsVoiceStrict=false&osType=api\"\n" +
                "}";

        Gson gson = new Gson();
        System.out.println(gson.fromJson(s, YouDaoTextTranslationResponse.class).getWeb());
    }
}
