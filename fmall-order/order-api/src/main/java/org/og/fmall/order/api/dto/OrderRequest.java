package org.og.fmall.order.api.dto;

import lombok.Data;
import org.og.fmall.commonapi.dto.RequestParam;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author:ougen
 * @date:2019/9/2419:33
 */
@Data
public class OrderRequest extends RequestParam {
    /**
     * orderId
     */
    private String id;

    @NotNull
    private Long sellerId;

    private Long memberId;

    @NotNull
    private Long fruitId;

    @NotNull
    private Long addressId;

    @NotEmpty
    private String streeName;

    private String nickName;

    private String tel;

    @NotEmpty
    private String fruitName;

    @NotNull
    private Integer orderTotal;

    @NotEmpty
    private String postWay;

    @NotNull
    private Integer freePost;

    @NotNull
    private BigDecimal eachPrice;

    private BigDecimal postFee;

    private String voucher;

    private BigDecimal voucherFee;

    //优惠活动
    private Integer favorableId;
    @NotNull
    private BigDecimal actualPay;
    @NotNull
    private BigDecimal idealPay;

    private String imgUrl;
}
