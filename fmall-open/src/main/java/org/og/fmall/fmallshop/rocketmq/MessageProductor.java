package org.og.fmall.fmallshop.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.og.fmall.commonapi.constants.RocketMQConstant;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.JSONUtil;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author:ougen
 * @date:2019/9/2713:12
 */
@Service
public class MessageProductor {
    private static Logger logger = LoggerFactory.getLogger(MessageProductor.class);
    @Value("${rocketmq.namesrvAddr:192.168.43.205:9876}")
    private String namesrvAddr;

    @Value("${rocketmq.productor.group:defaultGroup}")
    private String group;

    @Value("${rocketmq.productor.retryTimes:3}")
    private int retryTimes;

    private DefaultMQProducer producer;

    public OrderResponse sendOrderMessage(Message message)  {
        SendResult sendResult ;
        OrderResponse response = new OrderResponse();
        try {
            sendResult = producer.send(message);
        } catch (Exception e) {
            logger.error(e.toString());
            response.setCode(CommonEnum.ROCKETMQ_SEND_FAIL.getCode());
            response.setMsg("服务器忙，创建订单失败，请稍后重试");
            return response;
        }
        if (sendResult.getSendStatus() == SendStatus.SEND_OK){
            return response;
        }
        logger.warn(sendResult.getSendStatus().name());
        response.setCode(CommonEnum.ROCKETMQ_SEND_FAIL.getCode());
        response.setMsg("服务器忙，创建订单失败，请稍后重试"+sendResult.getSendStatus().name());
        return response;
    }

    @PostConstruct
    public void postConstruct() throws MQClientException {
        producer = new DefaultMQProducer(group);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setRetryTimesWhenSendFailed(retryTimes);
        producer.start();
    }
}
