package com.jason.tics.learn.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jason
 */
@Data
@Entity
@Table
public class Leitner {
    @Id
    private String cardUuid;
    private Integer boxId;
    private Integer dayInterval;
    private Long nextRepetition;
}
