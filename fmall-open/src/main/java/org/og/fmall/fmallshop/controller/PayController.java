package org.og.fmall.fmallshop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.remoting.TimeoutException;
import org.og.fmall.commonapi.exception.BaseException;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.member.verification.annotation.AccessKey;
import org.og.fmall.pay.api.dto.PayRequest;
import org.og.fmall.pay.api.dto.PayResponse;
import org.og.fmall.pay.api.service.IPayService;

import org.og.fmall.user.api.session.MemberSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author:ougen
 * @date:2019/10/120:16
 */
@RestController
public class PayController {

    private static Logger logger = LoggerFactory.getLogger(PayController.class);

    @Value("${pay.return.url:localhost:18080}")
    private String url;

    @Reference(check = false)
    private IPayService iPayService;

    @PostMapping("/pay")
    @AccessKey(accessLimit = 2)
    public Result<PayResponse> pay(PayRequest request, MemberSession memberSession, HttpServletResponse response) throws IOException {
        request.setMemberId(memberSession.getId());
        //这里提供的接口必须是其他公网可以访问的
        request.setNotifyUrl("http://"+url+"/pay/returnNotify");
        request.setReturnUrl("http://"+url+"/orderDetail.html?id="+request.getOrderId());
        request.checkParam();
        PayResponse payResponse = iPayService.pay(request);
        if (payResponse.getCode() != 0){
            throw new BaseException(payResponse.getCode(),payResponse.getMsg());
        }
        Result<PayResponse> result = ResultUtil.build();
        if (payResponse.getCode()!=0){
            result.setCode(payResponse.getCode());
            result.setMsg(payResponse.getMsg());
        }
        result.setData(payResponse);
        return result;
    }

    @PostMapping("/pay/returnNotify")
    public void retuNotify(HttpServletRequest request){
        //支付宝回调，由setNotifyUrl方法指定，这里指定必须是公网可以访问的
        //后续改进
        Map<String,String> params = new HashMap<String,String>();

        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        PayResponse response1;
        try {
            response1 = iPayService.notifyPay(params);
        }catch (Exception e){
            throw new BaseException("支付失败");
        }
        if (response1.getCode() == 0){
            logger.info("success={}");
        }else {
            logger.error("fail:{}",response1);
        }
    }
}
