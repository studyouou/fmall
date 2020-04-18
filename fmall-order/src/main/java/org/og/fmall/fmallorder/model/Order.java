package org.og.fmall.fmallorder.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/2418:34
 */
@Data
@Table(name = "tb_order")
public class Order {
    //订单号
    @Id
    @NotEmpty
    private String id;
    //水果id
    @NotNull
    @Column(name = "fruit_id")
    private Long fruitId;
    //水果名称
    @NotEmpty
    private String fruitName;

    private String imgUrl;

    @NotNull
    @Column(name = "seller_id")
    private Long sellerId;
    //用户id
    @NotNull
    @Column(name = "member_id")
    private Long memberId;
    //买家昵称
    @NotEmpty
    private String nickName;
    //用户地址id
    private Long addressId;
    //用户地址
    private String streeName;
    //创建时间
    private Date createTime;
    //最后修改时间
    private Date lastUpdateTime;
    //支付状态0未支付 1支付 -1取消
    private Integer orderState;
    //支付时间
    private Date payTime;
    //购买数量 斤
    @NotNull
    @Min(1)
    private Integer orderTotal;
    //单价
    private BigDecimal eachPrice;
    //应付金额
    private BigDecimal idealPay;
    //实付金额
    private BigDecimal actualPay;
    //邮寄方式
    private String postWay;
    //是否免邮 0否  1是
    private Integer freePost;
    //邮寄费用
    private BigDecimal postFee;
    //发货时间
    private Date consignTime;
    //优惠活动
    private Integer favorableId;
    //抵用券
    private String voucher;
    //抵用费用
    private BigDecimal voucherFee;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", fruitId=" + fruitId +
                ", fruitName='" + fruitName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", sellerId=" + sellerId +
                ", memberId=" + memberId +
                ", nickName='" + nickName + '\'' +
                ", addressId=" + addressId +
                ", streeName='" + streeName + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", orderState=" + orderState +
                ", payTime=" + payTime +
                ", orderTotal=" + orderTotal +
                ", eachPrice=" + eachPrice +
                ", idealPay=" + idealPay +
                ", actualPay=" + actualPay +
                ", postWay='" + postWay + '\'' +
                ", freePost=" + freePost +
                ", postFee=" + postFee +
                ", consignTime=" + consignTime +
                ", favorableId=" + favorableId +
                ", voucher='" + voucher + '\'' +
                ", voucherFee=" + voucherFee +
                '}';
    }
}