package org.og.fmall.fmalluser.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author:ougen
 * @date:2019/9/2911:19
 */
@Data
@Table(name = "tb_member")
public class Member {
    @Id
    private Long id;

    private Long tel;

    private String salt;

    private String password;

    private String nickName;

    private String email;

    private Integer age;

    private Integer sex;

    private Integer seller;

    private Integer status;

    private Date creatTime;

    private Date lastUpdateTime;
}
