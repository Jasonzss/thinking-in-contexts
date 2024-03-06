package com.jason.tics.order.controller.app;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.order.domain.Area;
import com.jason.tics.order.domain.City;
import com.jason.tics.order.domain.Province;
import com.jason.tics.order.domain.Street;
import com.jason.tics.order.repository.AreaRepository;
import com.jason.tics.order.repository.CityRepository;
import com.jason.tics.order.repository.ProvinceRepository;
import com.jason.tics.order.repository.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jason
 */
@RestController("/order/address")
public class AddressController {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private StreetRepository streetRepository;

    @GetMapping("/province")
    public ServerResponseEntity<List<Province>> listProvinces(){
        return ServerResponseEntity.success(provinceRepository.findAll());
    }

    @GetMapping("/province/{pcode}")
    public ServerResponseEntity<List<City>> listCities(@PathVariable String pcode){
        return ServerResponseEntity.success(cityRepository.findAllByProvinceCode(pcode));
    }

    @GetMapping("/province/{pcode}/city/{ccode}")
    public ServerResponseEntity<List<Area>> listArea(@PathVariable String pcode, @PathVariable String ccode){
        return ServerResponseEntity.success(areaRepository.findAllByProvinceCodeAndCityCode(pcode, ccode));
    }

    @GetMapping("/province/{pcode}/city/{ccode}/area/{acode}")
    public ServerResponseEntity<List<Street>> listStreet(@PathVariable String pcode, @PathVariable String ccode,
                                                         @PathVariable String acode){
        return ServerResponseEntity.success(
                streetRepository.findAllByProvinceCodeAndCityCodeAndAreaCode(pcode, ccode, acode));
    }
}
