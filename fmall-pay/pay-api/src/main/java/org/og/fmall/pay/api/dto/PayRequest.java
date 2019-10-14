package org.og.fmall.pay.api.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.exception.BaseException;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/10/1123:42
 */
@Data
public class PayRequest extends RequestParam {

    private String id;

    private String orderId;

    private Long memberId;

    private BigDecimal actualPay;

    private String fruitName;

    private Integer orderTotal;

    private BigDecimal eachPrice;

    private Date createTime;

    private Date lastUpdateTime;

    private String payInfo;

    private String returnUrl;

    private String notifyUrl;

    public void checkParam(){
        if (StringUtils.isEmpty(orderId)|| memberId == null || StringUtils.isEmpty(fruitName)  || actualPay == null
                || eachPrice == null || orderTotal == null){
            throw new BaseException(CommonEnum.PARAMERTER_WRONG.getCode(),CommonEnum.PARAMERTER_WRONG.getMsg());
        }
    }
}
