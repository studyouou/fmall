package org.og.fmall.user.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/2915:57
 */
@Data
public class MemberDto implements Serializable{

    private Long id;

    private Long tel;

    private String password;

    private String nickName;

    private String email;

    private Integer age;

    private Integer sex;

    private Integer seller;

    private Integer status;

    private String salt;
}
