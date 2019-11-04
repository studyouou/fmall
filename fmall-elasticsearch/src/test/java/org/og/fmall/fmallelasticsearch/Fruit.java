package org.og.fmall.fmallelasticsearch;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:ougen
 * @date:2019/9/2811:46
 */
@Data
public class Fruit {

    //水果id
    private Long id;

    //水果名
    private String fruitName;

    //水果商id
    private Long sellerId;

    //水果库存
    private Integer stock;

    //水果单价
    private BigDecimal eachPrice;

    //水果介绍
    private String disciption;

    //水果图片
    private String imgUrl;
}
