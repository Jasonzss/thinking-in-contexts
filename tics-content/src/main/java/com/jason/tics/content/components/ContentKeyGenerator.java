package com.jason.tics.content.components;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.content.domain.AudioPost;
import com.jason.tics.content.domain.EssayPost;
import com.jason.tics.content.domain.VideoPost;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class ContentKeyGenerator extends DefaultIdentifierGenerator implements IdentifierGenerator {
    @Override
    public String nextUUID(Object entity) {
        //前缀加上mp默认的雪花算法
        return getPrefix(entity)+nextId(entity);
    }

    private String getPrefix(Object entity){
        if (entity instanceof VideoPost){
            return "v";
        }else if(entity instanceof AudioPost){
            return "a";
        }else if(entity instanceof EssayPost){
            return "e";
        }else {
            throw new TicsException(ExceptionResponseEnum.NOT_SUPPORTED_ID_GENERATE_ENTITY);
        }
    }

    /**
     * 另外生成id的方式，待实现
     */
    private long getKey(Object entity){
        return 0L;
    }
}