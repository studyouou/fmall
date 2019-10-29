package org.og.fmall.fmallshop.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:  ougen
 * @description:
 * @date: 2019/10/29
 */
@Configuration
@EnableConfigurationProperties(RabbitmqProperties.class)
@ConditionalOnProperty(prefix = "rabbitmq",name = "enable",havingValue = "true")
public class RabbitmqConfig {

    @Autowired
    private RabbitmqProperties rabbitmqProperties;

    @Bean
    public ConnectionFactory autoConfiguration(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(rabbitmqProperties.getUsername());
        connectionFactory.setPassword(rabbitmqProperties.getPassword());
        connectionFactory.setHost(rabbitmqProperties.getHost());
        connectionFactory.setPort(rabbitmqProperties.getPort());
        connectionFactory.setVirtualHost(rabbitmqProperties.getVirtualHost());
        return connectionFactory;
    }
}
