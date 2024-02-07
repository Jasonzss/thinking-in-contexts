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
public class Sm2 {
    @Id
    private String cardUuid;
    private Integer repetitions;
    private Float easinessFactor;
    private Integer dayInterval;
    private Long nextRepetition;
}
