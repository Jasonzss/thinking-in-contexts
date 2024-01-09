package com.jason.tics.content.service.impl;

import cn.hutool.core.date.DateUtil;
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

    @Override
    public EssayPost updateEssay(EssayPost essayPost) {
        essayPostMapper.updateById(essayPost);
        return essayPost;
    }

    @Override
    public EssayPost updateEssayCover(String id, String cover) {
        EssayPost essayPost = new EssayPost();
        essayPost.setEssayId(id);
        essayPost.setCoverImageUrl(cover);
        essayPostMapper.updateById(essayPost);
        return essayPost;
    }

    @Override
    public EssayPost addEssay(EssayPostDto essayPostDto) {
        EssayPost essayPost = essayPostDto.getEssayPost();
        essayPost.setCreateTime(DateUtil.date());
        essayPostMapper.insert(essayPost);
        return essayPost;
    }

    @Override
    public void deleteEssay(String id) {
        essayPostMapper.deleteById(id);
    }
}
