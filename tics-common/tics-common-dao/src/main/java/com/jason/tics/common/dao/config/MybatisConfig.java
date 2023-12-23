package com.jason.tics.common.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jason
 */
@Configuration
@MapperScan({ "com.mall4j.cloud.**.mapper" })
public class MybatisConfig {

}
