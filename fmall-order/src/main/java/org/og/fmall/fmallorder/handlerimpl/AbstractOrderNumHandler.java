package org.og.fmall.fmallorder.handlerimpl;

import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;

/**
 * @author:ougen
 * @date:2019/9/2514:55
 */
public abstract class AbstractOrderNumHandler implements InvokeHandler {
    @Override
    public void handle(RequestParam requestParam, ResponseContext context) {
        OrderRequest orderRequest = (OrderRequest) requestParam;
        int orderTotal = orderRequest.getOrderTotal();
        long fruitId = orderRequest.getFruitId();
        queryAndDecrNum(orderTotal,fruitId, (OrderResponse) context);
    }

    protected abstract void queryAndDecrNum(int orderTotal, long fruitId, OrderResponse response);
}
