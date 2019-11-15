package org.og.fmall.fmallorder.handlerimpl;

import com.alibaba.dubbo.config.annotation.Reference;
import org.og.fmall.commonapi.annotation.Belong;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.fmallorder.exception.OrderNumOverException;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.fmallorder.factory.OrderPipeLineFactory;
import org.og.fmall.order.api.dto.OrderResponse;
import org.og.fmall.stock.api.iservice.IFruitQueryService;
import org.og.fmall.stock.api.iservice.IFruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/9/2515:01
 */
@Component
@Order(1)
@Belong(OrderPipeLineFactory.class)
public class RedisOrderNumHandler extends AbstractOrderNumHandler {
    @Autowired
    private RedisService redisService;

    @Override
    protected void queryAndDecrNum(int orderTotal, long fruitId, OrderResponse response) {
        String leaveNumStr = redisService.get(OrderConstants.FRUIT_NUM + fruitId);
        int leaveNum = 0;
        if (leaveNumStr == null || "".equals(leaveNumStr)){
//            redisService.set(OrderConstants.FRUIT_NUM+fruitId,leaveNum);
            return;
        }else {
            leaveNum = Integer.parseInt(leaveNumStr);
        }
        if (leaveNum < 0){
            response.setCode(CommonEnum.NUM_OVER.getCode());
            response.setMsg(CommonEnum.NUM_OVER.getMsg());
            return;
        }
        if (orderTotal > leaveNum){
            response.setCode(CommonEnum.NUM_OVER.getCode());
            response.setMsg(CommonEnum.NUM_OVER.getMsg());
            return;
        }
        redisService.decrBy(OrderConstants.FRUIT_NUM+fruitId,orderTotal);
    }
}
