package org.og.fmall.fmallstock.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author:ougen
 * @date:2019/9/2811:46
 */
@Data
@Table(name = "tb_fruit")
public class Fruit {

    //水果id
    @Id
    private Long id;

    //水果名
    private String fruitName;

    //水果商id
    @Column(name = "seller_id")
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
