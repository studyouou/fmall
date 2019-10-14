package org.og.fmall.fmallshop.iservice.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.rocketmq.common.message.Message;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commonapi.constants.RocketMQConstant;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.JSONUtil;
import org.og.fmall.commonapi.utils.OrderUtil;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.fmallshop.iservice.IShopOrderService;
import org.og.fmall.fmallshop.rocketmq.MessageProductor;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;
import org.og.fmall.order.api.iservice.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @author:ougen
 * @date:2019/9/2620:31
 */
@Service
public class IOrderServiceImpl implements IShopOrderService {

    @Reference(check = false)
    private IOrderService IOrderService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MessageProductor messageProductor;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) throws UnsupportedEncodingException {
        String orderId = OrderUtil.ceateOrderId(orderRequest.getMemberId());
        orderRequest.setId(orderId);
        OrderResponse order;
        String s ;
        //判断是否是热门商品，如果是，则发送消息创建订单，如果不是，则直接调用接口创建
        if ( (s = redisService.get(OrderConstants.FRUIT_NUM+orderRequest.getFruitId()))!=null && !"".equals(s)){
            Message message = new Message();
            message.setTopic(RocketMQConstant.OTDER_CREATE_TOPIC);
            message.setTags("default");
            message.setBody(JSONUtil.beanToString(orderRequest).getBytes("utf-8"));
            order = messageProductor.sendOrderMessage(message);
            order.setId(orderId);
            order.setIsMessage(1);
        }else {
            order = IOrderService.createOrder(orderRequest);
            order.setIsMessage(0);
        }
        return order;
    }
}
