package org.og.fmall.fmallshop.rabbitmq;

import lombok.Data;

/**
 * @author: lyp
 * @description:
 * @date: 2019/10/29
 */
@Data
public class RabbitMQMessage {

    private String exchange;

    private String queue;

    private byte[] body;

    private String routeKey;
}
