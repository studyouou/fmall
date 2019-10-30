package org.og.fmall.fmallorder;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.og.fmall.order.api.dto.OrderDto;
import org.og.fmall.order.api.dto.OrderResponse;
import org.og.fmall.order.api.iservice.IOrderQueryService;
import org.og.fmall.order.api.iservice.IOrderService;

/**
 * @author:ougen
 * @date:2019/10/220:54
 */
@Slf4j
public class OrderTest extends FmallOrderApplicationTests {

    @Reference(check = false)
    private IOrderQueryService iOrderQueryService;

    @Reference(check = false)
    private IOrderService iOrderService;

    @Test
    public void testCancelOrder(){
        OrderResponse response = iOrderService.cancelOrder("019100200001");
        log.info("testCancelOrder={}",response);
    }
    @Test
    public void testDeleteOrder(){
        OrderResponse response = iOrderService.deleteOrder("019100200001");
        log.info("deleteOrder={}",response);
    }
    @Test
    public void testQueryOrderById(){
        OrderDto orderDto = iOrderQueryService.queryOrderById("019100207214");
        log.info("testQueryOrderById={}",orderDto);
    }

    @Test
    public void testQueryAllOrderByMemberId(){
        PageInfo<OrderDto> orderDtoPageInfo = iOrderQueryService.queryPageOrderByMemberId(0L, 1, 10);
        log.info("testQueryAllOrderByMemberId={}",orderDtoPageInfo);
    }
}
