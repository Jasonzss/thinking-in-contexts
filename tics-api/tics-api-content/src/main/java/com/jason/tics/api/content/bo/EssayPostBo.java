package com.jason.tics.api.content.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 * @since 2023/09/13 - 14:58
 */
@Data
public class EssayPostBo {
    private String essayId;
    private Long authorId;
    private String title;
    private Long quantityOfViews;
    private String essayUrl;
    private String coverImageUrl;

    private Date createTime;
    private Date updateTime;


    public static final class EssayPostBoBuilder {
        private final EssayPostBo essayPostBo;

        private EssayPostBoBuilder() {
            essayPostBo = new EssayPostBo();
        }

        public static EssayPostBoBuilder anEssayPostBo() {
            return new EssayPostBoBuilder();
        }

        public EssayPostBoBuilder essayUrl(String essayUrl) {
            essayPostBo.setEssayUrl(essayUrl);
            return this;
        }

        public EssayPostBoBuilder coverImageUrl(String coverImageUrl) {
            essayPostBo.setCoverImageUrl(coverImageUrl);
            return this;
        }

        public EssayPostBoBuilder essayId(String essayId) {
            essayPostBo.setEssayId(essayId);
            return this;
        }

        public EssayPostBoBuilder authorId(Long authorId) {
            essayPostBo.setAuthorId(authorId);
            return this;
        }

        public EssayPostBoBuilder title(String title) {
            essayPostBo.setTitle(title);
            return this;
        }

        public EssayPostBoBuilder quantityOfViews(Long quantityOfViews) {
            essayPostBo.setQuantityOfViews(quantityOfViews);
            return this;
        }

        public EssayPostBoBuilder createTime(Date createTime) {
            essayPostBo.setCreateTime(createTime);
            return this;
        }

        public EssayPostBoBuilder updateTime(Date updateTime) {
            essayPostBo.setUpdateTime(updateTime);
            return this;
        }

        public EssayPostBo build() {
            return essayPostBo;
        }
    }
}
