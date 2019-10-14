package org.og.fmall.user.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/2916:46
 */
@Data
public class AddressDto implements Serializable {

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

    private String number;
}
