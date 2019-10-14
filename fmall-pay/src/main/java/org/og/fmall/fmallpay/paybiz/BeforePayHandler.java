package org.og.fmall.fmallpay.paybiz;

/**
 * @author:ougen
 * @date:2019/10/1122:59
 */

import com.alipay.api.domain.ExtendParams;
import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.commonapi.utils.JSONUtil;

import org.og.fmall.fmallpay.annotations.Belong;
import org.og.fmall.pay.api.dto.PayRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Belong(PayPipeLineFactory.class)
@Order(10)
@Component
public class BeforePayHandler implements InvokeHandler{
    @Override
    public void handle(RequestParam requestParam, ResponseContext context) {
        PayRequest request = (PayRequest) requestParam;
        ExtendParams extendParams = new ExtendParams();
        extendParams.setHbFqNum("9");
        extendParams.setHbFqSellerPercent("10");
        AlipayBean alipayBean = AlipayBean.builder().body(request.getFruitName()+"     ￥"+request.getEachPrice()+"x"+request.getOrderTotal())
                //配置支付可以付方式
                .enable_pay_channels("balance,moneyFund,pcredit")
                .out_trade_no(request.getOrderId())
                .product_code("FAST_INSTANT_TRADE_PAY")
                .total_amount(request.getActualPay().setScale(2).doubleValue())
                .subject(request.getFruitName())
                .passback_params("merchantBizType%3d3C%26merchantBizNo%3d2016010101111")
                .goods_type(1)
                .extend_params(extendParams).build();
        String s = JSONUtil.beanToString(alipayBean);
        request.setPayInfo(s);
    }
}
