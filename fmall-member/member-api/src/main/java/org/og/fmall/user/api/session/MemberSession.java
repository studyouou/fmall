package org.og.fmall.user.api.session;

import lombok.Data;

import java.util.Date;

/**
 * @author:ougen
 * @date:2019/10/10:00
 */
@Data
public class MemberSession {
    private Long id;

    private Long tel;

    private String salt;

    private String password;

    private String nickName;

    private String email;

    private Integer age;

    private Integer sex;

    private Integer seller;

    private Date creatTime;

    private String uuid;

}
