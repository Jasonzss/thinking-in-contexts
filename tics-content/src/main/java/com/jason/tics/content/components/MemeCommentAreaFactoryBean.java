package com.jason.tics.content.components;

import com.jason.tics.comment.meme.MemeCommentArea;
import com.jason.tics.comment.persistence.mybatis.MybatisMemeDaoFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class MemeCommentAreaFactoryBean implements FactoryBean<MemeCommentArea> {

    @Override
    public MemeCommentArea getObject() {
        MybatisMemeDaoFactory memeDaoFactory = new MybatisMemeDaoFactory(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/tics_content?useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                "root",
                "123456");

        return new MemeCommentArea(memeDaoFactory);
    }

    @Override
    public Class<?> getObjectType() {
        return MemeCommentArea.class;
    }
}
