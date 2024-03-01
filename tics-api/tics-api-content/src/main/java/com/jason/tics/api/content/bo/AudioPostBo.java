package com.jason.tics.api.content.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 15:01
 */
@Data
public class AudioPostBo {
    private String audioId;
    private Long authorId;
    private String title;
    private Long quantityOfViews;

    private String audioUrl;
    private String introduction;
    private String coverImageUrl;

    private Date createTime;
    private Date updateTime;


    public static final class AudioPostBoBuilder {
        private final AudioPostBo audioPostBo;

        private AudioPostBoBuilder() {
            audioPostBo = new AudioPostBo();
        }

        public static AudioPostBoBuilder anAudioPostBo() {
            return new AudioPostBoBuilder();
        }

        public AudioPostBoBuilder audioUrl(String audioUrl) {
            audioPostBo.setAudioUrl(audioUrl);
            return this;
        }

        public AudioPostBoBuilder introduction(String introduction) {
            audioPostBo.setIntroduction(introduction);
            return this;
        }

        public AudioPostBoBuilder coverImageUrl(String coverImageUrl) {
            audioPostBo.setCoverImageUrl(coverImageUrl);
            return this;
        }

        public AudioPostBoBuilder createTime(Date createTime) {
            audioPostBo.setCreateTime(createTime);
            return this;
        }

        public AudioPostBoBuilder updateTime(Date updateTime) {
            audioPostBo.setUpdateTime(updateTime);
            return this;
        }

        public AudioPostBoBuilder audioId(String audioId) {
            audioPostBo.setAudioId(audioId);
            return this;
        }

        public AudioPostBoBuilder authorId(Long authorId) {
            audioPostBo.setAuthorId(authorId);
            return this;
        }

        public AudioPostBoBuilder title(String title) {
            audioPostBo.setTitle(title);
            return this;
        }

        public AudioPostBoBuilder quantityOfViews(Long quantityOfViews) {
            audioPostBo.setQuantityOfViews(quantityOfViews);
            return this;
        }

        public AudioPostBo build() {
            return audioPostBo;
        }
    }
}
