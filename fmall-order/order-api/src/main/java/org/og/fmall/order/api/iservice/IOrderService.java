package org.og.fmall.order.api.iservice;

import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;

/**
 * @author:ougen
 * @date:2019/9/2418:31
 */
public interface IOrderService {

    /**
    * 创建订单
    * @author ougen
    * @param orderRequest
    * @return 创建订单信息
    */
    OrderResponse createOrder(OrderRequest orderRequest);

    /**
    * 取消订单，需要修改库存
    * @author ougen
    * @param id
    * @return 取消订单状态返回
    */
    OrderResponse cancelOrder(String id);

    //修改订单
    /**
    * 方法实现说明
    * @author ougen
    * @param orderRequest
    * @return 修改状态
    */
    OrderResponse updateOrder(OrderRequest orderRequest);

    /**
     * 删除订单
     * @param id
     * @return
     */
    OrderResponse deleteOrder(String id);


    /**
     * 支付订单，改变支付状态
     * @param id
     * @return
     */
    OrderResponse updateState(String id);

    /**
     * 退款接口
     * @return
     */
    OrderResponse returnFund();
}
