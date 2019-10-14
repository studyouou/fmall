package org.og.fmall.fmallshop.iservice;

import org.og.fmall.commonapi.result.Result;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;

import java.io.UnsupportedEncodingException;

/**
 * @author:ougen
 * @date:2019/9/2620:30
 */
public interface IShopOrderService {
    OrderResponse createOrder(OrderRequest orderRequest) throws UnsupportedEncodingException;
}
