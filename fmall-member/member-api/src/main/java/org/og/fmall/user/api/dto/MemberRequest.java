package org.og.fmall.user.api.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.exception.BaseException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author:ougen
 * @date:2019/9/2915:51
 */
@Data
public class MemberRequest extends RequestParam {

    private Long id;

    @NotNull
    private Long tel;

    @NotNull
    private String password;

    @NotEmpty
    private String salt;

    @NotEmpty
    @Email
    private String email;

    private String nickName;

    private String newPassword;

    private String rePassword;

    private Integer age;

    private Integer sex;

    private String uuid;

    public void checked(){
        if (tel ==null || StringUtils.isEmpty(password)){
            throw new BaseException(CommonEnum.REQUEST_CHECK_FAILURE.getCode(),CommonEnum.REQUEST_CHECK_FAILURE.getMsg());
        }
    }
}
