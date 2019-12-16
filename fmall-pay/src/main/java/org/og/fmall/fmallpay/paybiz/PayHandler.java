package org.og.fmall.fmallpay.paybiz;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import lombok.extern.slf4j.Slf4j;
import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;

import org.og.fmall.commonapi.enums.CommonEnum;

import org.og.fmall.fmallpay.annotations.Belong;
import org.og.fmall.fmallpay.config.AlipayConfig;
import org.og.fmall.pay.api.dto.PayRequest;
import org.og.fmall.pay.api.dto.PayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/10/1123:40
 */
@Belong(PayPipeLineFactory.class)
@Order(20)
@Component
@Slf4j
public class PayHandler implements InvokeHandler {
    private static Logger logger = LoggerFactory.getLogger(PayHandler.class);

    @Autowired
    private AlipayClient alipayClient;
    @Override
    public void handle(RequestParam requestParam, ResponseContext context) {
        PayResponse response = (PayResponse) context;
        PayRequest request = (PayRequest) requestParam;

        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        payRequest.setReturnUrl(request.getReturnUrl());
        payRequest.setNotifyUrl(request.getNotifyUrl());
        payRequest.setBizContent(request.getPayInfo());
        String form = null;
        try {
            form = alipayClient.pageExecute(payRequest).getBody();
        } catch (AlipayApiException e) {
            log.error(e.getErrMsg(),e);
            response.setCode(CommonEnum.FAIL.getCode());
            response.setMsg(CommonEnum.FAIL.getMsg());
            return;
        }
        response.setHtml(form);
    }
}
