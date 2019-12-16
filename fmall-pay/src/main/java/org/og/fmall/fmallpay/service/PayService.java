package org.og.fmall.fmallpay.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import lombok.extern.slf4j.Slf4j;
import org.og.fmall.commonapi.bussiness.invoke.PipeLineOutInvoke;
import org.og.fmall.commonapi.enums.CommonEnum;

import org.og.fmall.commonapi.utils.JSONUtil;
import org.og.fmall.fmallpay.config.AlipayConfig;
import org.og.fmall.fmallpay.paybiz.AlipayReturnFundBean;
import org.og.fmall.fmallpay.paybiz.PayPipeLineFactory;
import org.og.fmall.order.api.dto.OrderResponse;
import org.og.fmall.order.api.iservice.IOrderService;
import org.og.fmall.pay.api.dto.PayRequest;
import org.og.fmall.pay.api.dto.PayResponse;
import org.og.fmall.pay.api.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author:ougen
 * @date:2019/10/1122:40
 */
@Service
@Slf4j
public class PayService implements IPayService {

    @Reference(check = false)
    private IOrderService iOrderService;

    @Autowired
    private PayPipeLineFactory payPipeLineFactory;

    @Autowired
    private AlipayClient alipayClient;

    @Override
    public PayResponse pay(PayRequest payRequest) {
        PipeLineOutInvoke outInvoke = payPipeLineFactory.build(payRequest);
        outInvoke.start();
        PayResponse result = (PayResponse) outInvoke.getResult();
        return result;
    }

    @Override
    public PayResponse notifyPay(Map<String, String> params) {
        boolean signVerified = false;
        PayResponse response = new PayResponse();
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.CHARSET,AlipayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            log.error(e.getErrMsg(),e);
            params.put("refund_reason","签名错误");
            PayResponse payResponse = returnFund(params);
            if (payResponse.getCode()!=0){
                response.setCode(CommonEnum.FAIL.getCode());
                response.setMsg("退款失败，请联系商家");
                return response;
            }
            response.setCode(CommonEnum.FAIL.getCode());
            response.setMsg("签名验证错误,以退款，请重新下单");
            return response;
        }
        if(signVerified) {
            String orderId = params.get("out_trade_no");
            OrderResponse response1 = iOrderService.updateState(orderId);
            response.setCode(response1.getCode());
            response.setMsg(response1.getMsg());
            return response;
        }else {
            params.put("refund_reason","签名验证失败");
            PayResponse payResponse = returnFund(params);
            if(payResponse.getCode()!=0){
                response.setCode(CommonEnum.FAIL.getCode());
                response.setMsg("退款失败，请联系商家");
                return response;
            }
            response.setCode(CommonEnum.FAIL.getCode());
            response.setMsg("服务器错误,签名验证失败,退款成功");
            return response;
        }
    }

    @Override
    public PayResponse returnFund(Map<String, String> params) {
        PayResponse response = new PayResponse();
        AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
        String orderId = params.get("out_trade_no");
        AlipayReturnFundBean returnFundBean = AlipayReturnFundBean.builder().out_trade_no(orderId)
                .refund_amount(new BigDecimal(params.get("total_amount")).doubleValue())
                .refund_reason(params.get("refund_reason"))
                .out_request_no(params.get("out_request_no"))
                .operator_id(params.get("params"))
                .store_id(params.get("store_id")).build();
        String string = JSONUtil.beanToString(returnFundBean);
        refundRequest.setBizContent(string);
        AlipayTradeRefundResponse alipayTradePagePayResponse = null;
        try {
            alipayTradePagePayResponse = alipayClient.execute(refundRequest);
        } catch (AlipayApiException e) {
            log.error(e.getErrMsg(),e);
            response.setCode(CommonEnum.RETURN_FUND_WRONG.getCode());
            response.setMsg(CommonEnum.RETURN_FUND_WRONG.getMsg());
            return response;
        }
        if (alipayTradePagePayResponse.isSuccess()){
            return response;
        }else {
            response.setCode(CommonEnum.RETURN_FUND_WRONG.getCode());
            response.setMsg(CommonEnum.RETURN_FUND_WRONG.getMsg());
            return response;
        }
    }
}
