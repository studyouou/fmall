package org.og.fmall.stock.api.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author:ougen
 * @date:2019/9/2814:56
 */
@Data
public class FruitRequest implements Serializable {

    //水果名
    @NotEmpty
    private String fruitName;

    //水果商id
    @NotNull
    private long sellerId;

    //水果库存
    @NotNull
    @Min(0)
    private int stock;

    //水果单价
    @NotNull
    private BigDecimal eachPrice;

    //水果介绍
    @NotEmpty
    private String disciption;

    //水果图片
    private String imgUrl;
}
