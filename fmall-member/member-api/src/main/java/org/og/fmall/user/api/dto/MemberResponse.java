package org.og.fmall.user.api.dto;

import lombok.Data;
import org.og.fmall.commonapi.dto.ResponseContext;

/**
 * @author:ougen
 * @date:2019/9/2911:08
 */
@Data
public class MemberResponse extends ResponseContext{

    private Long id;

    private Long tel;

    private String userNick;

    private String city;

    private int age;

    private String uuid;
}
