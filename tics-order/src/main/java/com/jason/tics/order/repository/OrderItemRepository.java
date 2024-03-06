package com.jason.tics.order.repository;

import com.jason.tics.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
