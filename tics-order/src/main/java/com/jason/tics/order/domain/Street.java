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
public class Street {
    @Id
    private String code;
    private String name;
    private String provinceCode;
    private String cityCode;
    private String areaCode;
}