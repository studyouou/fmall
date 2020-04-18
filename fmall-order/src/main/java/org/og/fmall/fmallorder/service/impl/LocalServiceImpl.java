package org.og.fmall.fmallorder.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.fmallorder.mapper.OrderMapper;
import org.og.fmall.fmallorder.model.Order;
import org.og.fmall.fmallorder.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: OuGen
 * @create: 2020-04-16 09:43
 **/
@Service
public class LocalServiceImpl implements LocalService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public int createPrepareLocalOrder(BusinessActionContext actionContext, Order order) {
        System.out.println("--------------------------------执行order的createPrepareLocalOrder操作-------------------------------------------");
        int i = orderMapper.insertSelective(order);
        if (i <= 0){
            throw new RuntimeException("订单创建失败，订单信息:"+order.toString());
        }
        return i;
    }

    @Override
    public boolean orderCreateCommit(BusinessActionContext actionContext) {
        System.out.println("--------------------------------执行order的commit操作-------------------------------------------");
        JSONObject context = (JSONObject)actionContext.getActionContext("order");
        Order order = context.toJavaObject(Order.class);
        redisService.decrBy(OrderConstants.FRUIT_NUM+order.getId(),order.getOrderTotal());
        redisService.set(OrderConstants.ORDER_SUBMIT_KEY+order.getId(),"true",60);
        return true;
    }

    @Override
    public boolean orderCreateRollback(BusinessActionContext actionContext) {
        System.out.println("--------------------------------执行order的rollback操作-------------------------------------------");
        JSONObject context = (JSONObject)actionContext.getActionContext("order");
        Order order = context.toJavaObject(Order.class);
        orderMapper.deleteByPrimaryKey(order.getId());
        return true;
    }
}
