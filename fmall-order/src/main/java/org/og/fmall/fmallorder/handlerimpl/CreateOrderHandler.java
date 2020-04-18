package org.og.fmall.fmallorder.handlerimpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.og.fmall.commonapi.annotation.Belong;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.fmallorder.factory.OrderPipeLineFactory;
import org.og.fmall.fmallorder.mapper.OrderAckMapper;
import org.og.fmall.fmallorder.mapper.OrderMapper;
import org.og.fmall.fmallorder.model.Order;
import org.og.fmall.fmallorder.model.OrderAck;
import org.og.fmall.fmallorder.service.LocalService;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;
import org.og.fmall.stock.api.dto.FruitResponse;
import org.og.fmall.stock.api.iservice.IFruitQueryService;
import org.og.fmall.stock.api.iservice.IFruitService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/2614:41
 */
@org.springframework.core.annotation.Order(10)
@Belong(OrderPipeLineFactory.class)
@Service
public class CreateOrderHandler implements InvokeHandler {

    @Reference(check = false)
    private IFruitService iFruitService;

    @Reference(check = false)
    private IFruitQueryService iFruitQueryService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderAckMapper orderAckMapper;

    @Autowired
    private LocalService localService;

    private ApplicationContext applicationContext;

    @Override
    @GlobalTransactional
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void handle(RequestParam requestParam, ResponseContext context) {
        OrderRequest orderRequest = (OrderRequest) requestParam;
        OrderResponse response = (OrderResponse) context;
        Order order = createOrder(orderRequest);
        FruitResponse response1;
        localService.createPrepareLocalOrder(null,order);
//        response1 = iFruitService.reduceNum(order.getFruitId(), order.getOrderTotal());
//        if (response1.getCode()!=0){
//            redisService.set(OrderConstants.FRUIT_NUM+fruitId,0);
//            response.setCode(response1.getCode());
//            response.setMsg(response1.getMsg());
//            return;
//        }
        //使用dubbo-spring-boot=start 自动扫描Reference，但是Reference不会到spring容器中
        iFruitService.reduceOrderPrepare(order.getFruitId(),order.getOrderTotal());
        pacResponse(response, order, orderRequest);
    }
    private void pacResponse(OrderResponse response, Order order, OrderRequest orderRequest) {
        BeanUtils.copyProperties(order,response);
        response.setStreeName(orderRequest.getStreeName());
        response.setTel(orderRequest.getTel());
        response.setCreateTime(order.getCreateTime());
    }

    private Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        BeanUtils.copyProperties(orderRequest,order);
        order.setCreateTime(new Date());
        order.setOrderState(0);
        order.setOrderTotal(orderRequest.getOrderTotal());
        order.setActualPay(orderRequest.getActualPay());
        order.setIdealPay(orderRequest.getIdealPay());
        order.setCreateTime(new Date());
        return order;
    }

}
