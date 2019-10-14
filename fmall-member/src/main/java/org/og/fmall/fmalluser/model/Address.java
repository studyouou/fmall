package org.og.fmall.fmalluser.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/2916:40
 */
@Table(name = "tb_member_address")
@Data
public class Address {

    @Id
    private Long id;

    private Long memberId;

    private String reciveName;

    private Long tel;

    private String prov;

    private String city;

    private String area;

    private String streeName;

    private Integer defaultAddress;

    private Date createTime;

    private Date lastUpdateTime;

    private String number;
}
