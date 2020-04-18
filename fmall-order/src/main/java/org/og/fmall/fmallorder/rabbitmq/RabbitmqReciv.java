package org.og.fmall.fmallorder.rabbitmq;

import com.rabbitmq.client.*;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.exception.BaseException;
import org.og.fmall.commonapi.utils.JSONUtil;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;
import org.og.fmall.order.api.iservice.IOrderService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lyp
 * @description:
 * @date: 2019/10/29
 */
@Component
@ConditionalOnProperty(prefix = "rabbitmq",name = "enable",havingValue = "true")
public class RabbitmqReciv implements InitializingBean {

    private ConnectionFactory connectionFactory;

    private IOrderService iOrderService;

    @Value("${rabbitmq.consumer.channel.num:40}")
    private int num;

    public RabbitmqReciv(ConnectionFactory connectionFactory,IOrderService iOrderService){
        this.connectionFactory = connectionFactory;
        this.iOrderService = iOrderService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Connection connection = connectionFactory.newConnection();
        for (int i=0; i < num; i++){
            Channel channel = connection.createChannel();
            Map<String,Object> args = new HashMap<>();
            args.put("x-dead-letter-exchange","order_dead_letter_exchange");
            args.put("x-dead-letter-routing-key","order_queue_fail_key");

            //业务队列交换机等
            channel.queueDeclare(OrderConstants.ORDER_QUEUE_NAME,true,false,false,null);
            channel.exchangeDeclare(OrderConstants.ORDER_DIRECT_EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true,false,null);
            channel.queueBind(OrderConstants.ORDER_QUEUE_NAME,OrderConstants.ORDER_DIRECT_EXCHANGE_NAME,OrderConstants.ROUTE_KEY);

            //死信队列及交换机
            channel.queueDeclare("order_dead",true,false,true,null);
            channel.exchangeDeclare("order_dead_letter_exchange",BuiltinExchangeType.DIRECT);
            channel.queueBind("order_dead","order_dead_letter_exchange","order_queue_fail_key");
            channel.basicConsume(OrderConstants.ORDER_QUEUE_NAME,false,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    OrderRequest request = JSONUtil.stringToBean(new String(body,"utf-8"),OrderRequest.class);
                    try {
                        OrderResponse orderResponse = iOrderService.createOrder(request);
                        if (orderResponse.getCode() == CommonEnum.SUCCESS.getCode()){
                            channel.basicAck(envelope.getDeliveryTag(),false);
                            return;
                        }
                        if (orderResponse.getCode() == CommonEnum.UNKOW_ERROR.getCode()){
                            channel.basicNack(envelope.getDeliveryTag(),false,true);
                        }else {
                            channel.basicNack(envelope.getDeliveryTag(),false,false);
                        }
                    }catch (Exception e){
                        channel.basicNack(envelope.getDeliveryTag(),false,false);
                    }

                }
            });
        }
    }
}
