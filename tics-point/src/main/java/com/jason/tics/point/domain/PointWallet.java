package com.jason.tics.point.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Jason
 */
@Data
@Entity
@Table
public class PointWallet {
    @Id
    private Long uid;
    /**
     * 余额
     */
    private BigDecimal balance;
}
