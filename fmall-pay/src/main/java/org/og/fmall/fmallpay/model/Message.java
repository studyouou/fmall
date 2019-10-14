package org.og.fmall.fmallpay.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/10/1220:15
 */
@Data
@Table(name = "tb_message")
public class Message {

    @Id
    private Long id;

    private Long memberId;

    private String massage;

    //0 未读 1已读
    private Integer state;

    private Date createTime;

    private Date lastUpdateTime;
}
