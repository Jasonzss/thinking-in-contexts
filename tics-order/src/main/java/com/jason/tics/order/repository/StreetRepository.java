package com.jason.tics.order.repository;

import com.jason.tics.order.domain.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 */
public interface StreetRepository extends JpaRepository<Street, String> {
    List<Street> findAllByProvinceCodeAndCityCodeAndAreaCode(String provinceCode, String cityCode, String areaCode);
}
