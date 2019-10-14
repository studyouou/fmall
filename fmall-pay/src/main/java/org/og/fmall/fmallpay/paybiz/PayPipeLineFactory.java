package org.og.fmall.fmallpay.paybiz;

import org.og.fmall.commonapi.bussiness.factory.pipeline.AbstractPipeLineFactory;
import org.og.fmall.commonapi.bussiness.invoke.PipeLineOutInvoke;
import org.og.fmall.commonapi.dto.RequestParam;

import org.og.fmall.pay.api.dto.PayRequest;
import org.og.fmall.pay.api.dto.PayResponse;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/10/1122:56
 */
@Component
public class PayPipeLineFactory extends AbstractPipeLineFactory {
    @Override
    protected PipeLineOutInvoke createOutInvoke(RequestParam requetParam) {
        PayResponse response = new PayResponse();
        PayRequest payRequest = (PayRequest) requetParam;
        return new PayPipeLineOutInvoke(payRequest,response);
    }
}
