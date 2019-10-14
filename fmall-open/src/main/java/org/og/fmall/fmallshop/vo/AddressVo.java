package org.og.fmall.fmallshop.vo;

import lombok.Data;

/**
 * @author:ougen
 * @date:2019/10/312:01
 */
@Data
public class AddressVo {

    private Long id;

    private String reciveName;

    private Long tel;

    private String prov;

    private String city;

    private String area;

    private String streeName;

    private Integer defaultAddress;
}
