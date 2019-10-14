package org.og.fmall.fmallshop.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:ougen
 * @date:2019/10/421:10
 */
@Data
public class PanelVo {
    private Long id;

    //水果id
    private Long fruitId;

    //panel图片地址
    private String panelImgUrl;

    //水果名
    private String fruitName;

    private Long sellerId;

    private BigDecimal eachPrice;
}
