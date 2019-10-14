package org.og.fmall.order.api.dto;

import lombok.Data;
import org.og.fmall.commonapi.dto.RequestParam;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/2419:56
 */
@Data
public class OrderDto extends RequestParam{

    private String id;

    private Long memberId;

    private Long fruitId;

    private String fruitName;

    private String imgUrl;

    private Long addressId;

    private String streeName;

    private Date createTime;

    private String tel;

    private String nickName;

    private Integer orderTotal;

    private BigDecimal eachPrice;

    private Integer orderState;

    private BigDecimal idealPay;

    private BigDecimal actualPay;

    private BigDecimal postFee;

    private String voucher;

    private BigDecimal voucherFee;

    private Integer favorableId;

    private String imgUtl;

}
