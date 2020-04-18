package org.og.fmall.fmallshop.rabbitmq;

import com.rabbitmq.client.*;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.exception.BaseException;
import org.og.fmall.commonapi.rabbitmq.DataContainer;
import org.og.fmall.commonapi.rabbitmq.MyDataChannel;
import org.og.fmall.commonapi.rabbitmq.MyDataListener;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
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
    @Value("${rabbitmq.producer.channelNum:30}")
    private int channelNum;

    @Autowired
    private ConnectionFactory connectionFactory;

    private ArrayBlockingQueue<MyDataChannel> channels ;


    public OrderResponse sendMessage(RabbitMQMessage message, BuiltinExchangeType type, String id) throws IOException, TimeoutException {
        MyDataChannel channel = null;
        try {
            while (channel == null){
                channel = channels.poll(1, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("channel can not get");
        }
        try {
            OrderResponse response = new OrderResponse();
            channel.basicPublish(message.getExchange(),message.getRouteKey(), new AMQP.BasicProperties().builder().deliveryMode(2).build(),message.getBody(),id);
            return response;
        }finally {
            channels.offer(channel);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Connection connection = connectionFactory.newConnection();
        channels = new ArrayBlockingQueue<>(channelNum);
        for (int i = 0; i < channelNum; i++){
            Channel channel = connection.createChannel();
            if (i == 0){
                channel.queueDeclare(OrderConstants.ORDER_DIRECT_EXCHANGE_NAME,true,false,false,null);
                channel.exchangeDeclare(OrderConstants.ORDER_DIRECT_EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true,false,null);
                channel.queueBind(OrderConstants.ORDER_QUEUE_NAME,OrderConstants.ORDER_DIRECT_EXCHANGE_NAME,OrderConstants.ROUTE_KEY);
            }
            MyDataChannel myDataChannel = new MyDataChannel(channel);
            channels.add(myDataChannel);
        }
    }
}
