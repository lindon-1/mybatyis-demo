package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.proxy.DruidDriver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 11:11
 */
@Configuration
public class DbConfig {
//    @Bean
//    public DataSource druidDataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setPassword("123456");
//        druidDataSource.setUsername("root");
//        druidDataSource.setDriver(new DruidDriver());
//        druidDataSource.setUrl("jdbc:mysql://localhost:3306/user");
//        return druidDataSource;
//    }

}
