package com.jason.tics.recommend.domain.preference;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jason
 */
@Entity
@Data
@Table
public class EssayTastePreferences {
    @Id
    private Long userId;
    @Id
    private Long itemId;
    private Float preference;
    private Long timestamp;
}
