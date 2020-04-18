package org.og.fmall.fmallorder.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.fmallorder.mapper.OrderMapper;
import org.og.fmall.fmallorder.model.Order;
import org.og.fmall.order.api.dto.OrderDto;
import org.og.fmall.order.api.dto.OrderResponse;
import org.og.fmall.order.api.iservice.IOrderQueryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:ougen
 * @date:2019/9/2816:54
 */
@Service
public class OrderQueryService implements IOrderQueryService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public PageInfo<OrderDto> queryPageOrderByMemberId(Long memberId,Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Order> orders = orderMapper.selectOrderByMemberId(memberId);
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        List<OrderDto> orderDtos = new ArrayList<>();
        if (orders == null){
            return null;
        }
        for (Order order:orders){
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order,orderDto);
            orderDtos.add(orderDto);
        }
        PageInfo<OrderDto> pageInfo1 = new PageInfo();
        BeanUtils.copyProperties(pageInfo,pageInfo1);
        pageInfo1.setList(orderDtos);
        return pageInfo1;
    }

    @Override
    public List<OrderDto> queryAllOrderByMemberId(Long memberId) {
        List<Order> orders = orderMapper.selectOrderByMemberId(memberId);
        List<OrderDto> orderDtos = new ArrayList<>();
        if (orders == null){
            return null;
        }
        for (Order order:orders){
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order,orderDto);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Override
    public OrderDto queryOrderById(String id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        if (order == null){
            return null;
        }
        OrderDto orderDto = new OrderDto() ;
        BeanUtils.copyProperties(order,orderDto);
        return orderDto;
    }

    @Override
    public Integer exeistOrder(Long id,Long fruitId) {

        String s = redisService.get(OrderConstants.ORDER_SUBMIT_KEY + id);
        if (StringUtils.isNotEmpty(s)){
            return 1;
        }
        boolean b = orderMapper.existsWithPrimaryKey(id);
        if (b){
            return 1;
        }
        if (Integer.parseInt(redisService.get(OrderConstants.FRUIT_NUM+fruitId)) <= 0){
            return -1;
        }
        return 0;
    }
}
