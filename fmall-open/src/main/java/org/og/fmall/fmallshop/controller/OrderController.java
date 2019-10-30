package org.og.fmall.fmallshop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.rabbitmq.client.BuiltinExchangeType;
import org.apache.rocketmq.common.message.Message;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commonapi.constants.RocketMQConstant;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.JSONUtil;
import org.og.fmall.commonapi.utils.OrderUtil;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.fmallshop.rabbitmq.RabbitMQMessage;
import org.og.fmall.fmallshop.rabbitmq.RabbitMQSendMessage;
import org.og.fmall.fmallshop.rocketmq.RocketMQMessageProductor;
import org.og.fmall.fmallshop.vo.OrderVo;
import org.og.fmall.member.verification.annotation.AccessKey;
import org.og.fmall.order.api.dto.OrderDto;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;
import org.og.fmall.order.api.iservice.IOrderQueryService;
import org.og.fmall.order.api.iservice.IOrderService;
import org.og.fmall.stock.api.iservice.IFruitQueryService;
import org.og.fmall.user.api.dto.AddressDto;
import org.og.fmall.user.api.iservice.IAddressQueryService;
import org.og.fmall.user.api.session.MemberSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author:ougen
 * @date:2019/9/2620:18
 */
@RestController
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Reference(check = false,timeout = 6000)
    private IOrderService iOrderService;

    @Reference(check = false,timeout = 6000)
    private IOrderQueryService iOrderQueryService;

    @Reference(check = false,timeout = 6000)
    private IFruitQueryService iFruitQueryService;

    @Reference(check = false,timeout = 6000,retries = 0)
    private IAddressQueryService iAddressQueryService;

    @Autowired
    private RedisService redisService;

    @Autowired(required = false)
    private RocketMQMessageProductor rocketMQMessageProductor;

    @Autowired(required = false)
    private RabbitMQSendMessage rabbitMQSendMessage;

    @PostMapping("/order")
    @AccessKey
    public Result<OrderResponse> createOrder(@Valid OrderRequest orderRequest, BindingResult bindingResult,MemberSession session) throws IOException, TimeoutException {
        Result<OrderResponse> result = ResultUtil.build();
        OrderResponse response = null;
        if (bindingResult.hasErrors()){
            result.setCode(CommonEnum.FAIL.getCode());
            result.setMsg(bindingResult.getFieldError().getField()+"不能为空");
            return result;
        }
        String orderId = OrderUtil.ceateOrderId(session.getId());
        orderRequest.setId(orderId);
        orderRequest.setMemberId(session.getId());
        String s ;
        //判断是否是热门商品，如果是，则发送消息创建订单，如果不是，则直接调用接口创建
        if ( (s = redisService.get(OrderConstants.FRUIT_NUM+orderRequest.getFruitId()))!=null && !"".equals(s) ){
            if (rabbitMQSendMessage==null && rocketMQMessageProductor==null){
                throw new RuntimeException("热门消息未初始化消息队列");
            }
            if (rabbitMQSendMessage != null && Integer.parseInt(s)>0){
                logger.info("热门商品，rabbitmq消息队列发送创建订单");
                RabbitMQMessage message = new RabbitMQMessage();
                message.setExchange(OrderConstants.ORDER_DIRECT_EXCHANGE_NAME);
                message.setQueue(OrderConstants.ORDER_QUEUE_NAME);
                message.setRouteKey(OrderConstants.ROUTE_KEY);
                message.setBody(JSONUtil.beanToString(orderRequest).getBytes("utf-8"));
                response = rabbitMQSendMessage.sendMessage(message, BuiltinExchangeType.DIRECT);
                response.setId(orderId);
                response.setFruitId(orderRequest.getFruitId());
                response.setIsMessage(1);
            }else if (rocketMQMessageProductor != null && Integer.parseInt(s)>0){
                logger.info("热门商品，rocketmq消息队列发送创建订单");
                Message message = new Message();
                message.setTopic(RocketMQConstant.OTDER_CREATE_TOPIC);
                message.setTags("default");
                message.setBody(JSONUtil.beanToString(orderRequest).getBytes("utf-8"));
                response = rocketMQMessageProductor.sendOrderMessage(message);
                response.setId(orderId);
                response.setFruitId(orderRequest.getFruitId());
                response.setIsMessage(1);
            }
        }else {
            logger.info("调用接口创建订单");
            response = iOrderService.createOrder(orderRequest);
            response.setIsMessage(0);
        }
        result.setCode(response.getCode());
        result.setMsg(response.getMsg());
        result.setData(response);
        return result;
    }

    @PutMapping("/{id}/order")
    @AccessKey
    public Result<OrderResponse> cancelOrder(@NotEmpty @PathVariable("id") String id){
        Result<OrderResponse> result = ResultUtil.build();
        OrderResponse response = iOrderService.cancelOrder(id);
        result.setCode(response.getCode());
        result.setMsg(response.getMsg());
        result.setData(response);
        return result;
    }

    @DeleteMapping("/{id}/order")
    @AccessKey
    public Result<OrderResponse> deleteOrder(@NotEmpty @PathVariable("id") String id){
        Result<OrderResponse> result = ResultUtil.build();
        OrderResponse response = iOrderService.deleteOrder(id);
        result.setCode(response.getCode());
        result.setMsg(response.getMsg());
        result.setData(response);
        return result;
    }

    @GetMapping("/{id}/order")
    @AccessKey(accessLimit = 20)
    public Result<OrderVo> queryOrderDetail(@NotNull @PathVariable("id") String id){
        OrderDto orderDto = iOrderQueryService.queryOrderById(id);
        Result<OrderVo> result = ResultUtil.build();
        if (orderDto == null){
            result.setCode(CommonEnum.ORDER_NOT_FOUND.getCode());
            result.setMsg(CommonEnum.ORDER_NOT_FOUND.getMsg());
            return result;
        }
        AddressDto addressDto = iAddressQueryService.queryAddressById(orderDto.getAddressId());
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(orderDto,orderVo);
        orderVo.setArea(addressDto.getArea());
        orderVo.setCity(addressDto.getCity());
        orderVo.setProv(addressDto.getProv());
        orderVo.setReciveName(addressDto.getReciveName());
        result.setData(orderVo);
        return result;
    }

    @GetMapping("/orders/all")
    @AccessKey(accessLimit = 20)
    public Result<List<OrderVo>> queryOrders(MemberSession memberSession){
        List<OrderDto> orderDtos = iOrderQueryService.queryAllOrderByMemberId(memberSession.getId());
        Result<List<OrderVo>> result = ResultUtil.build();
        if (orderDtos == null || orderDtos.size()==0){
            result.setCode(CommonEnum.MEMBER_NOT_ORDER.getCode());
            result.setMsg(CommonEnum.MEMBER_NOT_ORDER.getMsg());
            return result;
        }
        List<OrderVo> orderVos = new ArrayList<>();
        for (OrderDto orderDto : orderDtos){
            OrderVo orderVo = new OrderVo();
            orderVo.setCreateTime(orderDto.getCreateTime());
            orderVo.setOrderState(orderDto.getOrderState());
            orderVo.setFruitName(orderDto.getFruitName());
            orderVos.add(orderVo);
        }
        result.setCode(CommonEnum.SUCCESS.getCode());
        result.setMsg(CommonEnum.SUCCESS.getMsg());
        result.setData(orderVos);
        return result;
    }

    @GetMapping("/orders")
    @AccessKey(accessLimit = 20)
    public Result<PageInfo<OrderVo>> queryPageOrders(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page
            ,MemberSession memberSession){
        PageInfo<OrderDto> pageInfo = iOrderQueryService.queryPageOrderByMemberId(memberSession.getId(),page,pageSize);
        Result<PageInfo<OrderVo>> result = ResultUtil.build();
        if (pageInfo == null){
            result.setCode(CommonEnum.MEMBER_NOT_ORDER.getCode());
            result.setMsg(CommonEnum.MEMBER_NOT_ORDER.getMsg());
            return result;
        }
        List<OrderVo> orderVos = new ArrayList<>();
        for (OrderDto orderDto : pageInfo.getList()){
            OrderVo orderVo = new OrderVo();
            orderVo.setId(orderDto.getId());
            orderVo.setCreateTime(orderDto.getCreateTime());
            orderVo.setOrderState(orderDto.getOrderState());
            orderVo.setFruitName(orderDto.getFruitName());
            orderVos.add(orderVo);
        }
        PageInfo<OrderVo> pageInfoVo = new PageInfo();
        BeanUtils.copyProperties(pageInfo,pageInfoVo);
        pageInfoVo.setList(orderVos);
        result.setCode(CommonEnum.SUCCESS.getCode());
        result.setMsg(CommonEnum.SUCCESS.getMsg());
        result.setData(pageInfoVo);
        return result;
    }

    @GetMapping("/{id}/getResult")
    @AccessKey
    public Result<Integer> getOrderResult(@PathVariable("id") Long id,@RequestParam("fruitId")Long fruitId){
        Result result = ResultUtil.build();
        Integer b = iOrderQueryService.exeistOrder(id,fruitId);
        result.setData(b);
        return result;
    }
}
