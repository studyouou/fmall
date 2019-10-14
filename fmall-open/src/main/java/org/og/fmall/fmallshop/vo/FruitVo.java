package org.og.fmall.fmallshop.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:ougen
 * @date:2019/10/923:11
 */
@Data
public class FruitVo {
    private long id;

    //水果名
    private String fruitName;

    //水果商id
    private long sellerId;

    //水果库存
    private int stock;

    //水果单价
    private BigDecimal eachPrice;

    //水果介绍
    private String disciption;

    //水果图片
    private String imgUrl;

    private BigDecimal voucherFee;

    private BigDecimal favorablePrice;
}
