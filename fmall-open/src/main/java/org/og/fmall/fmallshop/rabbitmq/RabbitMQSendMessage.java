package org.og.fmall.fmallshop.rabbitmq;

import com.rabbitmq.client.*;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.exception.BaseException;
import org.og.fmall.order.api.dto.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:  ougen
 * @description:
 * @date: 2019/10/29
 */
@Service
@ConditionalOnBean(RabbitmqConfig.class)
public class RabbitMQSendMessage implements InitializingBean {

    private static final  Logger logger = LoggerFactory.getLogger(RabbitMQSendMessage.class);

    @Value("${rabbitmq.producer.retries:1}")
    private int retries;

    @Autowired
    private ConnectionFactory connectionFactory;

    private Connection connection;


    public OrderResponse sendMessage(RabbitMQMessage message,BuiltinExchangeType type) throws IOException, TimeoutException {
        while (!connection.isOpen()){
            synchronized (this) {
                Connection connection = connectionFactory.newConnection();
                this.connection = connection;
            }
        }
        Channel channel = connection.createChannel();
        OrderResponse response = new OrderResponse();
        AMQP.Confirm.SelectOk selectOk = channel.confirmSelect();
        channel.queueDeclare(message.getQueue(),true,false,false,null);
        channel.exchangeDeclare(message.getExchange(), type,true,false,null);
        channel.queueBind(message.getQueue(),message.getExchange(),message.getRouteKey());
        channel.basicPublish(message.getExchange(),message.getRouteKey(), new AMQP.BasicProperties().builder().deliveryMode(2).build(),message.getBody());
        int count = 0;
        while (retries > count){
            try {
                if (channel.waitForConfirms(2000)){
                    return response;
                }
            } catch (InterruptedException | TimeoutException e) {
                channel.basicPublish(OrderConstants.ORDER_DIRECT_EXCHANGE_NAME,OrderConstants.ROUTE_KEY, new AMQP.BasicProperties().builder().deliveryMode(2).build(),"".getBytes());
            }
        }
        throw new BaseException(CommonEnum.ROCKETMQ_SEND_FAIL.getCode(),CommonEnum.ROCKETMQ_CONSUMER_FAIL.getMsg());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Connection connection = connectionFactory.newConnection();
        this.connection = connection;
    }
}
