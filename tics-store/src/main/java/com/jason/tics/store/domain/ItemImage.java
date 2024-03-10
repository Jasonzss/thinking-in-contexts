package com.jason.tics.store.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Jason
 */
@Entity
@Table
@Data
public class ItemImage {
    @EmbeddedId
    private ItemImageId itemImageId;
    private String url;

    @Embeddable
    @Data
    public static class ItemImageId implements Serializable {
        private Long itemId;
        private Integer imageIndex;

        public ItemImageId() {
        }

        public ItemImageId(Long itemId, Integer imageIndex) {
            this.itemId = itemId;
            this.imageIndex = imageIndex;
        }
    }
}
