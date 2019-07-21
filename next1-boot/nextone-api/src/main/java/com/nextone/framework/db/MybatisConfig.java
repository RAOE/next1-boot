package com.nextone.framework.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-07-15
 * @description
 **/
public class MybatisConfig {
    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean()
    {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
//        bean.setTypeAli
        bean.setTypeAliasesPackage("com.nextone.pojo");
        try {
            return  bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
