package com.jason.tics.order.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jason
 */
@Entity
@Table
@Data
public class Province {
    @Id
    private String code;
    private String name;
}
