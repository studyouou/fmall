package org.og.fmall.fmallorder.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:ougen
 * @date:2019/10/2612:41
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    @ConditionalOnProperty(prefix = "spring.rabbitmq",name = "enable",havingValue = "true")
    public AmqpTemplate amqpTemplate(){
        return new RabbitTemplate();
    }
}
