package org.og.fmall.fmallorder.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.og.fmall.commontools.druid.DruidProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * @author:ougen
 * @date:2019/9/2512:36
 */

@Configuration
@EnableConfigurationProperties(DruidProperties.class)
public class DruidInitial {

    @Autowired
    private DruidProperties druidProperties;

    @Value("${rabbitmq.enable}")
    private String enable;

    @Bean
    public DruidDataSource druidDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(druidProperties.getUrl());
        druidDataSource.setUsername(druidProperties.getUsername());
        druidDataSource.setPassword(druidProperties.getPassword());
        druidDataSource.setDriverClassName(druidProperties.getDriverClassName());
        druidDataSource.setInitialSize(druidProperties.getInitialSize());
        druidDataSource.setMaxActive(druidProperties.getMaxActive());
        druidDataSource.setMinIdle(druidProperties.getMinIdle());
        druidDataSource.setTestWhileIdle(druidProperties.isTestWhileIdle());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setValidationQuery(druidProperties.getValidationQuery());
        druidDataSource.setMaxWait(druidProperties.getMaxWait());
        druidDataSource.init();
        return druidDataSource;
    }
}
