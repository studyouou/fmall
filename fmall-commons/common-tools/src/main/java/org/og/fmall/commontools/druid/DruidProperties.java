package org.og.fmall.commontools.druid;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author:ougen
 * @date:2019/9/2512:26
 */

@ConfigurationProperties("spring.datasource")
@Data
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
