package com.jason.tics.comment.persistence.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Jason
 */
public class MybatisTest {
    protected static DruidDataSource dataSource;
    protected static final Logger log = LoggerFactory.getLogger(MybatisTest.class);

    static {
        dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tics_comment?useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    protected static SqlSession openSessionByCode(){
        JdbcTransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMappers("com.jason.tics.comment.persistence.mybatis.mapper");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory.openSession();
    }

    protected static SqlSession openSessionByXmlAndProp() {
        Properties prop = new Properties();
        prop.put("driver","com.mysql.cj.jdbc.Driver");
        prop.put("url","jdbc:mysql://localhost:3306/tics_comment?useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        prop.put("username","root");
        prop.put("password","123456");

        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SqlSessionFactoryBuilder().build(inputStream, prop).openSession();
    }
}
