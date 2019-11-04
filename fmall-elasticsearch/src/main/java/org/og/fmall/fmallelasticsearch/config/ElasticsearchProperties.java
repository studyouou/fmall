package org.og.fmall.fmallelasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: og
 * @description:
 * @date: 2019/11/4
 */
@ConfigurationProperties(prefix = "elasticsearch")
@Data
public class ElasticsearchProperties {
    private String host;
    private int port;
}
