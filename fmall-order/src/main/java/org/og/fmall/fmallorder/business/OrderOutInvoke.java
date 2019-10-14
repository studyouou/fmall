package org.og.fmall.fmallorder.business;

import org.og.fmall.commonapi.bussiness.invoke.AbstractPipeLineOutInvoke;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;

/**
 * @author:ougen
 * @date:2019/9/2513:09
 */
public class OrderOutInvoke extends AbstractPipeLineOutInvoke {

    public OrderOutInvoke(OrderRequest orderRequest,OrderResponse orderResponse){
        super(orderRequest,orderResponse);
    }
}
