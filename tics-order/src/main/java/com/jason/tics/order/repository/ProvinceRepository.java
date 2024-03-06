package com.jason.tics.order.repository;

import com.jason.tics.order.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface ProvinceRepository extends JpaRepository<Province, String> {
}
