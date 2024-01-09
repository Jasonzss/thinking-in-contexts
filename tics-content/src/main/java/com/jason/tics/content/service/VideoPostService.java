package com.jason.tics.content.service;

import cn.hutool.db.PageResult;
import com.jason.tics.content.domain.VideoPost;
import com.jason.tics.content.domain.dto.VideoPostDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jason
 */
public interface VideoPostService {
    VideoPost getVideo(String id);

    // 增删改

    VideoPost addVideo(VideoPostDto videoPostDto);

    VideoPost updateVideo(String id, String file);

    VideoPost updateTitle(String id, String title);

    VideoPost updateIntroduction(String id, String introduction);

    VideoPost updateCoverImage(String id, String cover);

    void deleteVideo(String id);
}
