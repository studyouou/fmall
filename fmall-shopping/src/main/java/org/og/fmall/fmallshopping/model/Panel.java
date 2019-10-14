package org.og.fmall.fmallshopping.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/10/421:59
 */
@Data
public class Panel {

    private Long id;

    //水果id
    private Long fruitId;

    //panel图片地址
    private String panelImgUrl;

    private Long sellerId;

    private BigDecimal eachPrice;
    //水果名
    private String fruitName;

    //顺序大小
    private Integer order;

    //状态 0 表示过期，1表示未过期
    private Integer state;

    //剩余轮训时间
    private Integer aliveTime;

    //panel位置 0代表最上层轮播界面，1代表下层轮播界面，2代表上层panel，3代表下层panel
    private Integer panelLocation;

    private Date createTime;

    private Date lastUpdateTime;

}
