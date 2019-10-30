package org.og.fmall.fmallorder.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/10/2618:40
 */

@Component
public class OrderConsumer {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @RabbitListener()
    public void listener(){

    }

}
