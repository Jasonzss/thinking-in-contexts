package com.jason.tics.store.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.jason.tics.common.jpa.config.PersistenceJPAConfig;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Jason
 */
@Configuration
@AutoConfigureBefore(PersistenceJPAConfig.class)
public class JpaConfig {
    @Bean
    public DataSource dataSource()  {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tics_store?useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }
}
