package org.og.fmall.order.api.dto;

import lombok.Data;
import org.og.fmall.commonapi.dto.ResponseContext;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/2419:27
 */

@Data
public class OrderResponse extends ResponseContext {
    private int isMessage;

    private String id;

    private String streeName;

    private String tel;

    private Long fruitId;

    private String fruitName;

    private int orderState;

    private BigDecimal idealPay;

    private BigDecimal actualPay;

    private Date createTime;
}
