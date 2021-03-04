package com.pcode.application.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

import static com.pcode.application.config.MybatisSessionFactory.createSqlSessionFactory;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.pcode.demo.dao", sqlSessionFactoryRef = "commSqlSessionFactory")
public class MybatisCommConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid.comm")
    public DataSource commDataSource() {
        log.info("comm 库连接");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory commSqlSessionFactory() throws Exception {
        return createSqlSessionFactory(commDataSource());
    }

    @Bean
    public DataSourceTransactionManager commTransaction() {
        return new DataSourceTransactionManager(commDataSource());
    }

    @Bean
    public JdbcTemplate commJdbcTemplate() {
        return new JdbcTemplate(commDataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate commNamedTemplate() {
        return new NamedParameterJdbcTemplate(commDataSource());
    }
}
