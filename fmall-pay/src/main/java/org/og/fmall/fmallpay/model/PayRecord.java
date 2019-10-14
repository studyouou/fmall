package org.og.fmall.fmallpay.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/10/1220:15
 */
@Table(name = "tb_pay_record")
@Data
public class PayRecord {

    @Id
    private Long id;

    private Long orderId;

    private Long memberId;

    private BigDecimal payMoney;

    private Date createTime;

    private Date lastUpdateTime;
}
