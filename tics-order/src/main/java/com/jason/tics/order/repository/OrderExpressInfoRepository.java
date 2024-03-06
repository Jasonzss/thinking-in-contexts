package com.jason.tics.order.repository;

import com.jason.tics.order.domain.OrderExpressInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface OrderExpressInfoRepository extends JpaRepository<OrderExpressInfo, Long> {
}
