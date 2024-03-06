package com.jason.tics.order.repository;

import com.jason.tics.order.domain.OrderPayInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface OrderPayInfoRepository extends JpaRepository<OrderPayInfo, Long> {
}
