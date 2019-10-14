package org.og.fmall.pay.api.service;


import org.og.fmall.pay.api.dto.PayRequest;
import org.og.fmall.pay.api.dto.PayResponse;

import java.util.Map;

/**
 * @author:ougen
 * @date:2019/10/1122:40
 */
public interface IPayService {
    PayResponse pay(PayRequest payRequest);

    PayResponse notifyPay(Map<String, String> params);

    PayResponse returnFund(Map<String, String> params);
}
