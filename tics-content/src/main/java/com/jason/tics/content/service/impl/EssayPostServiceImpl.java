package com.jason.tics.content.service.impl;

import com.jason.tics.api.content.bo.EssayPostBo;
import com.jason.tics.common.rocketmq.annotation.SendMessage;
import com.jason.tics.common.rocketmq.aop.PayloadSource;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.content.domain.EssayPost;
import com.jason.tics.content.domain.dto.EssayPostDto;
import com.jason.tics.content.mapper.EssayPostMapper;
import com.jason.tics.content.service.EssayPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class EssayPostServiceImpl implements EssayPostService {
    @Autowired
    private EssayPostMapper essayPostMapper;

    @Override
    public EssayPost getEssay(String id) {
        return essayPostMapper.selectById(id);
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_ESSAY_UPSERT_TOPIC, targetPojo = EssayPostBo.class)
    @Override
    public EssayPost updateEssay(EssayPost essayPost) {
        essayPostMapper.updateById(essayPost);
        return essayPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_ESSAY_UPSERT_TOPIC, targetPojo = EssayPostBo.class)
    @Override
    public EssayPost updateEssayCover(String id, String cover) {
        EssayPost essayPost = new EssayPost();
        essayPost.setEssayId(id);
        essayPost.setCoverImageUrl(cover);
        essayPostMapper.updateById(essayPost);
        return essayPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_ESSAY_UPSERT_TOPIC, targetPojo = EssayPostBo.class)
    @Override
    public EssayPost addEssay(EssayPostDto essayPostDto, long uid) {
        EssayPost essayPost = essayPostDto.getEssayPost();
        essayPost.setAuthorId(uid);
        essayPostMapper.insert(essayPost);
        return essayPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_ESSAY_DELETE_TOPIC, source = PayloadSource.PARAM)
    @Override
    public void deleteEssay(String id) {
        essayPostMapper.deleteById(id);
    }
}
