package org.og.fmall.fmallpay.paybiz;

import com.alipay.api.domain.ExtendParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:ougen
 * @date:2019/9/1016:23
 */


@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class AlipayBean {
    private String out_trade_no;
    private String product_code;
    private Double total_amount;
    private String subject;
    private ExtendParams extend_params;
    private String body;
    private String passback_params;
    private int goods_type;
    private String timeout_express;
    private String enable_pay_channels;
    private String request_from_url;
    private String business_params;

}
