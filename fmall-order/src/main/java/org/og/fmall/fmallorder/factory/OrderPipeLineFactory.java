package org.og.fmall.fmallorder.factory;

import org.og.fmall.commonapi.bussiness.factory.pipeline.AbstractPipeLineFactory;
import org.og.fmall.fmallorder.business.OrderOutInvoke;
import org.og.fmall.commonapi.bussiness.invoke.PipeLineOutInvoke;
import org.og.fmall.fmallorder.handlerimpl.CreateOrderHandler;
import org.og.fmall.fmallorder.handlerimpl.LogsCollectForOrderHandler;
import org.og.fmall.fmallorder.handlerimpl.RedisOrderNumHandler;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/9/2513:34
 */
@Component
public class OrderPipeLineFactory extends AbstractPipeLineFactory<OrderRequest> {

    @Override
    protected PipeLineOutInvoke createOutInvoke(OrderRequest bean) {
        OrderOutInvoke orderOutInvoke = new OrderOutInvoke(bean,new OrderResponse());
        return orderOutInvoke;
    }


}
