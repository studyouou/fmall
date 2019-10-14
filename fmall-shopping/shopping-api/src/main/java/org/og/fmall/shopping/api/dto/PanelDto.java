package org.og.fmall.shopping.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author:ougen
 * @date:2019/10/421:56
 */
@Data
public class PanelDto implements Serializable{

    private Long id;

    //水果id
    private Long fruitId;

    private Long sellerId;

    private BigDecimal eachPrice;

    //panel图片地址
    private String panelImgUrl;

    //水果名
    private String fruitName;

}
