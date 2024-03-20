package com.jason.tics.user.domain.dto;

import com.jason.tics.common.jpa.entity.SimpleDto;
import com.jason.tics.user.domain.UserAddress;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jason
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserAddressDto extends SimpleDto<Long, UserAddress> {
    private Long uid;

    private Integer userAddressId;

    private String addressTag;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 省份、地区
     */
    private String provinceId;

    /**
     * 城市
     */
    private String cityId;

    /**
     * 区、县
     */
    private String areaId;

    /**
     * 街道、镇
     */
    private String streetId;

    /**
     * 详细地址
     */
    private String address;

    @Override
    public Class<UserAddress> getSourceClass() {
        return UserAddress.class;
    }
}
