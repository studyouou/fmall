package org.og.fmall.fmallorder.handlerimpl;

import lombok.extern.slf4j.Slf4j;
import org.og.fmall.commonapi.annotation.Belong;
import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.fmalllogs.productor.Productor;
import org.og.fmall.fmallmail.core.EmailClient;
import org.og.fmall.fmallorder.factory.OrderPipeLineFactory;
import org.og.fmall.order.api.dto.OrderRequest;
import org.og.fmall.order.api.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Order(20)
@Belong(OrderPipeLineFactory.class)
@Component
@Slf4j
@ConditionalOnProperty(prefix = "kafka" , name = "enable",havingValue = "true", matchIfMissing = true)
public class LogsCollectForOrderHandler implements InvokeHandler {

    @Autowired
    private Productor productor;

    @Autowired
    private EmailClient emailClient;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ss hh:mm:ss");

    @Override
    public void handle(RequestParam requestParam, ResponseContext context) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            OrderRequest order = (OrderRequest)requestParam;
            OrderResponse response = (OrderResponse) context;
            stringBuilder.append(order.getId()).append(" ").append(simpleDateFormat.format(response.getCreateTime())).append(" ")
                    .append(order.getMemberId()).append(" ")
                    .append(order.getFruitId()).append(" ")
                    .append(order.getOrderTotal()).append(" ")
                    .append(order.getFruitName()).append(" ")
                    //暂时支付宝
                    .append("支付宝");
            productor.sendMessage(stringBuilder.toString());
        }catch (Exception e){
            log.error("发送日志失败，失败异常信息为:"+e.getMessage());
        }

    }
}
