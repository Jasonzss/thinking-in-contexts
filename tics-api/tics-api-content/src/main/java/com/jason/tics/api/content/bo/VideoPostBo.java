package com.jason.tics.api.content.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 15:00
 */
@Data
public class VideoPostBo {
    private String videoId;
    private Long authorId;
    private String title;
    private Long quantityOfViews;

    private String videoUrl;
    private String introduction;
    private String coverImageUrl;

    private Date createTime;
    private Date updateTime;


    public static final class VideoPostBoBuilder {
        private final VideoPostBo videoPostBo;

        private VideoPostBoBuilder() {
            videoPostBo = new VideoPostBo();
        }

        public static VideoPostBoBuilder aVideoPostBo() {
            return new VideoPostBoBuilder();
        }

        public VideoPostBoBuilder videoUrl(String videoUrl) {
            videoPostBo.setVideoUrl(videoUrl);
            return this;
        }

        public VideoPostBoBuilder introduction(String introduction) {
            videoPostBo.setIntroduction(introduction);
            return this;
        }

        public VideoPostBoBuilder coverImageUrl(String coverImageUrl) {
            videoPostBo.setCoverImageUrl(coverImageUrl);
            return this;
        }

        public VideoPostBoBuilder createTime(Date createTime) {
            videoPostBo.setCreateTime(createTime);
            return this;
        }

        public VideoPostBoBuilder updateTime(Date updateTime) {
            videoPostBo.setUpdateTime(updateTime);
            return this;
        }

        public VideoPostBoBuilder videoId(String videoId) {
            videoPostBo.setVideoId(videoId);
            return this;
        }

        public VideoPostBoBuilder authorId(Long authorId) {
            videoPostBo.setAuthorId(authorId);
            return this;
        }

        public VideoPostBoBuilder title(String title) {
            videoPostBo.setTitle(title);
            return this;
        }

        public VideoPostBoBuilder quantityOfViews(Long quantityOfViews) {
            videoPostBo.setQuantityOfViews(quantityOfViews);
            return this;
        }

        public VideoPostBo build() {
            return videoPostBo;
        }
    }
}
