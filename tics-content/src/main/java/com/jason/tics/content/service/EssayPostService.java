package com.jason.tics.content.service;

import com.jason.tics.content.domain.EssayPost;
import com.jason.tics.content.domain.dto.EssayPostDto;

/**
 * @author Jason
 */
public interface EssayPostService {
    EssayPost getEssay(String id);

    EssayPost updateEssay(EssayPost essayPost);

    EssayPost updateEssayCover(String id, String cover);

    EssayPost addEssay(EssayPostDto essayPostDto);

    void deleteEssay(String id);
}
