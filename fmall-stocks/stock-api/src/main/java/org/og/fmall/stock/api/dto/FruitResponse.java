package org.og.fmall.stock.api.dto;

import lombok.Data;
import org.og.fmall.commonapi.dto.ResponseContext;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author:ougen
 * @date:2019/9/2814:57
 */
@Data
public class FruitResponse extends ResponseContext {
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

    private int reduceStock;

    private int incrementStock;
}
