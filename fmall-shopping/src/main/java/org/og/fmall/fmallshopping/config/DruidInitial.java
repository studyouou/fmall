package org.og.fmall.fmallshopping.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.og.fmall.commontools.druid.DruidProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DruidProperties druitConfig;

    @Bean
    public DruidDataSource druidDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(druitConfig.getUrl());
        druidDataSource.setUsername(druitConfig.getUsername());
        druidDataSource.setPassword(druitConfig.getPassword());
        druidDataSource.setDriverClassName(druitConfig.getDriverClassName());
        druidDataSource.setInitialSize(druitConfig.getInitialSize());
        druidDataSource.setMaxActive(druitConfig.getMaxActive());
        druidDataSource.setMinIdle(druitConfig.getMinIdle());
        druidDataSource.setValidationQuery(druitConfig.getValidationQuery());
        druidDataSource.setMaxWait(druitConfig.getMaxWait());
        druidDataSource.init();
        return druidDataSource;
    }
}
