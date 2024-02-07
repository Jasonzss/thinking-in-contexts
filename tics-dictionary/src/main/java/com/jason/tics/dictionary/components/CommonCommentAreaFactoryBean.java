package com.jason.tics.dictionary.components;

import com.jason.tics.comment.core.CommonCommentArea;
import com.jason.tics.comment.persistence.mybatis.MybatisJCommentDaoFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class CommonCommentAreaFactoryBean implements FactoryBean<CommonCommentArea> {
    @Override
    public CommonCommentArea getObject() {
        MybatisJCommentDaoFactory mybatisJCommentDaoFactory = new MybatisJCommentDaoFactory(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/tics_dictionary?useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                "root",
                "123456"
        );

        return new CommonCommentArea(mybatisJCommentDaoFactory);
    }

    @Override
    public Class<?> getObjectType() {
        return CommonCommentArea.class;
    }
}
