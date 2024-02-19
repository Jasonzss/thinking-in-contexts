package com.jason.tics.dictionary.service.impl;

import com.jason.tics.dictionary.service.QueryStringService;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class QueryStringServiceImpl implements QueryStringService {


    /**
     * 处理查询字符串
     * 去除无用的空格
     * TODO 去除无用字符还需再考虑一下
     * "  a bcd    e     fg  " -> "a bcd e fg"
     */
    @Override
    public String handleQueryString(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        boolean f = false;
        for (int i = 0; i < s.length(); i++) {
            char c;
            if ((c = s.charAt(i)) != ' ') {
                if(f){
                    sb.append(' ');
                    f = false;
                }
                sb.append(c);
            }else {
                f = true;
            }
        }
        return sb.toString();
    }
}
