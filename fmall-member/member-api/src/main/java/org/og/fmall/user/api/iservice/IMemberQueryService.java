package org.og.fmall.user.api.iservice;

import org.og.fmall.user.api.dto.MemberDto;

/**
 * @author:ougen
 * @date:2019/9/2911:07
 */
public interface IMemberQueryService {

    /**
     * 通过手机号查询用户
     * @param tel
     * @return
     */
    MemberDto queryMemerByTel(Long tel);

    /**
     * 通过邮箱查用户
     * @param email
     * @return
     */
    MemberDto queryMemberByEmail(String email);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    MemberDto queryMemberById(Long id);
}
