package org.og.fmall.fmallpay.paybiz;

import com.alipay.api.domain.ExtendParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author:ougen
 * @date:2019/9/1019:03
 */

@Data
@AllArgsConstructor
@Builder
public class AlipayReturnFundBean {
    private String trade_no;
    private String out_trade_no;
    private String out_request_no;
    private Double refund_amount;
    private String refund_reason;
    private String biz_type;
    private String operator_id;
    private String store_id;
    private String terminal_id;
    private ExtendParams extend_params;
}
