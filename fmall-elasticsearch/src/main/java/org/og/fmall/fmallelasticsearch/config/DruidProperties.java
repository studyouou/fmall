package org.og.fmall.fmallelasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: og
 * @description:
 * @date: 2019/11/4
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidProperties {
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private long maxWait;
    private String validationQuery;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String type;
    private boolean testWhileIdle;
    private long timeBetweenEvictionRunsMillis;
}