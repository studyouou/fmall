package org.og.fmall.fmallshop.rabbitmq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: lyp
 * @description:
 * @date: 2019/10/29
 */
@ConfigurationProperties(prefix = "rabbitmq")
@Data
public class RabbitmqProperties {

    private String host = "localhost";

    private Integer port = 5672;

    private String username;

    private String password;

    private String virtualHost = "/";
}
