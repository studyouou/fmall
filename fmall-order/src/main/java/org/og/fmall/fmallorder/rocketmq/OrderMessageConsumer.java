package org.og.fmall.fmallorder.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.og.fmall.commonapi.constants.RocketMQConstant;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.JSONUtil;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;
import org.og.fmall.order.api.iservice.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Rocketmq消费order订单，重试次数
 *
 * @author:ougen
 * @date:2019/9/2710:58
 */
@Service
@ConditionalOnProperty(prefix = "rocketmq",name = "enable",havingValue = "true")
public class OrderMessageConsumer implements InitializingBean{
    private static Logger logger = LoggerFactory.getLogger(OrderMessageConsumer.class);

    @Value("${rocketmq.namesrvAddr:192.168.43.205:9876}")
    private String namesrvAddr;

    @Value("${rocketmq.consumer.group:defaultGroup}")
    private String group;

    @Value("${rocketmq.consumer.reconsumeTimes:3}")
    private int reconsumeTimes;

    @Autowired
    private IOrderService IOrderService;

    private DefaultMQPushConsumer consumer;
    @PostConstruct
    public void postConstuct(){
        logger.info("postConstuct start");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("afterPropertiesSet start");
        consumer = new DefaultMQPushConsumer(group);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.subscribe(RocketMQConstant.OTDER_CREATE_TOPIC,"default");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                MessageExt messageExt = msgs.get(0);
                String data = new String(messageExt.getBody());
                OrderRequest orderRequest = JSONUtil.stringToBean(data, OrderRequest.class);
                try {
                    OrderResponse response = IOrderService.createOrder(orderRequest);
                    if (response.getCode() == 0){
                        logger.info("订单创建成功:{}",response);
                    }else {
                        logger.info(response.getMsg());
                    }
                }catch (Exception e){
                    logger.info("第{}次创建订单失败",messageExt.getReconsumeTimes());
                    if (messageExt.getReconsumeTimes() == reconsumeTimes){
                        logger.error("订单创建失败={}",e.getMessage());
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        logger.info("orderConsumer started");
    }
}