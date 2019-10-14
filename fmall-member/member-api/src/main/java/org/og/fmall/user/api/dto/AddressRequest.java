package org.og.fmall.user.api.dto;

import lombok.Data;
import org.og.fmall.commonapi.dto.RequestParam;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/2916:49
 */
@Data
public class AddressRequest extends RequestParam {

    private Long id;

    private Long memberId;

    @NotEmpty
    private String reciveName;

    @NotNull
    private Long tel;

    @NotEmpty
    private String prov;

    @NotNull
    private String city;

    @NotNull
    private String area;

    @NotNull
    private String streeName;

    private Integer defaultAddress;

}
