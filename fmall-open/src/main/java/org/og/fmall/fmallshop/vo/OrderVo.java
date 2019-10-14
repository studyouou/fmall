package org.og.fmall.fmallshop.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/3021:52
 */
@Data
public class OrderVo {
    private String id;

    private Long tel;

    private String streeName;

    private String imgUrl;

    private Date createTime;

    private Date payTime;

    private String reciveName;

    private String prov;

    private String city;

    private String area;

    private String fruitName;

    private Integer orderState;

    private Integer orderTotal;

    private BigDecimal eachPrice;

    private BigDecimal actualPay;

    private BigDecimal idealPay;

    private BigDecimal postFee;

    private String voucher;

    private BigDecimal voucherFee;

    private BigDecimal favorablePrice;

}
