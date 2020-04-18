package org.og.fmall.fmallorder.model;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @description:
 * @author: OuGen
 * @create: 2020-04-13 14:40
 **/
@Data
@Table(name = "tb_order_ack")
public class OrderAck {

    private Integer id;

    private String orderId;

    private Date createTime;

    private String state;
}
