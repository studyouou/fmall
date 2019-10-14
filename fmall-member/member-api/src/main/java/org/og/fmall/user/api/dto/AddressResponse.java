package org.og.fmall.user.api.dto;

import lombok.Data;
import org.og.fmall.commonapi.dto.ResponseContext;

import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/2916:47
 */
@Data
public class AddressResponse extends ResponseContext {

    private Long id;

    private Long memberId;

    private String reciveName;

    private Long tel;

    private String streeName;
}
