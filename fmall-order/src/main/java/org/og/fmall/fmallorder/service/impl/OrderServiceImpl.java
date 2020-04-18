package org.og.fmall.fmallorder.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.exception.BaseException;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.commonapi.bussiness.invoke.OutInvoke;
import org.og.fmall.fmallorder.factory.OrderPipeLineFactory;
import org.og.fmall.fmallorder.mapper.OrderMapper;
import org.og.fmall.fmallorder.model.Order;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;
import org.og.fmall.order.api.iservice.IOrderService;
import org.og.fmall.stock.api.dto.FruitResponse;
import org.og.fmall.stock.api.iservice.IFruitService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/2418:31
 */
@Service
@org.springframework.stereotype.Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Reference(check = false)
    private IFruitService fruitService;

    @Autowired
    private OrderPipeLineFactory orderPipeLineFactory;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        OutInvoke outInvoke = orderPipeLineFactory.build(orderRequest);
        outInvoke.start();
        OrderResponse response = (OrderResponse) outInvoke.getResult();
        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderResponse cancelOrder(String id) {
        Result<OrderResponse> result = ResultUtil.build();
        Order order = orderMapper.selectByPrimaryKey(id);
        OrderResponse response = new OrderResponse();
        response.setId(id);
        if (order == null){
            response.setCode(CommonEnum.ORDER_NOT_FOUND.getCode());
            response.setMsg(CommonEnum.ORDER_NOT_FOUND.getMsg());
            return response;
        }
        if (order.getOrderState() == 1){
            response.setCode(CommonEnum.CANNOT_CANCEL_OF_PAY.getCode());
            response.setMsg(CommonEnum.CANNOT_CANCEL_OF_PAY.getMsg());
            return response;
        }
        if (order.getOrderState()==-1){
            response.setCode(CommonEnum.ORDER_HAS_CANCEL.getCode());
            response.setMsg(CommonEnum.ORDER_HAS_CANCEL.getMsg());
            return response;
        }
        order.setOrderState(-1);
        int i = orderMapper.updateByPrimaryKeySelective(order);
        FruitResponse response1 = null;
        if (i > 0){
            response1 = fruitService.incrementNum(order.getFruitId(),order.getOrderTotal());
        }
        if (response1 == null){
            throw new BaseException(CommonEnum.UNKOW_ERROR.getCode(),CommonEnum.UNKOW_ERROR.getMsg());
        }
        response.setFruitName(order.getFruitName());
        return response;
    }

    @Override
    public OrderResponse updateOrder(OrderRequest orderRequest) {
        Order order = new Order();
        OrderResponse response = new OrderResponse();
        response.setId(orderRequest.getId());
        BeanUtils.copyProperties(orderRequest,order);
        orderMapper.updateByPrimaryKeySelective(order);
        BeanUtils.copyProperties(orderRequest,response);
        return response;
    }

    @Override
    @Transactional
    public OrderResponse deleteOrder(String id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(id);
        if (order == null){
            orderResponse.setCode(CommonEnum.ORDER_NOT_FOUND.getCode());
            orderResponse.setMsg(CommonEnum.ORDER_NOT_FOUND.getMsg());
            return orderResponse;
        }
        int i = 0;
        if (order.getOrderState()==0){
            FruitResponse response = fruitService.incrementNum(order.getFruitId(), order.getOrderTotal());
            if (response.getCode() != 0){
                throw new BaseException(response.getCode(),response.getMsg());
            }
            i = orderMapper.deleteByPrimaryKey(id);
            if (i == 0){
                throw new BaseException(CommonEnum.ORDER_DELETE_UNKNOW.getCode(),CommonEnum.ORDER_DELETE_UNKNOW.getMsg());
            }
            return orderResponse;
        }
        i = orderMapper.deleteByPrimaryKey(id);
        if (i == 0){
            throw new BaseException(CommonEnum.ORDER_DELETE_UNKNOW.getCode(),CommonEnum.ORDER_DELETE_UNKNOW.getMsg());
        }
        return orderResponse;
    }


    @Override
    public OrderResponse updateState(String id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        OrderResponse response = new OrderResponse();
        response.setId(id);
        if (order == null){
            response.setCode(CommonEnum.ORDER_NOT_FOUND.getCode());
            response.setMsg(CommonEnum.ORDER_NOT_FOUND.getMsg());
            return response;
        }
        if (order.getOrderState() == 1){
            response.setCode(CommonEnum.ORDER_HAS_PAY.getCode());
            response.setMsg(CommonEnum.ORDER_HAS_PAY.getMsg());
            return response;
        }
        if (order.getOrderState() == -1){
            response.setCode(CommonEnum.ORDER_HAS_CANCEL.getCode());
            response.setMsg(CommonEnum.ORDER_HAS_CANCEL.getMsg());
            return response;
        }
        Date now = new Date();
        order.setOrderState(1);
        order.setPayTime(now);
        order.setLastUpdateTime(now);
        orderMapper.updateByPrimaryKeySelective(order);
        return response;
    }

    @Override
    public OrderResponse returnFund() {
        return null;
    }

}
