package org.og.fmall.fmallpay.paybiz;

import org.og.fmall.commonapi.bussiness.invoke.AbstractPipeLineOutInvoke;
import org.og.fmall.pay.api.dto.PayRequest;
import org.og.fmall.pay.api.dto.PayResponse;

/**
 * @author:ougen
 * @date:2019/10/1122:57
 */
public class PayPipeLineOutInvoke extends AbstractPipeLineOutInvoke {
    public PayPipeLineOutInvoke(PayRequest payRequest, PayResponse payResponse) {
        super(payRequest, payResponse);
    }
}
