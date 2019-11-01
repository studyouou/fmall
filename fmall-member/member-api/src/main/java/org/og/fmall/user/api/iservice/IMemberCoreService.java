package org.og.fmall.user.api.iservice;

import org.og.fmall.commonapi.tkmapper.TkMapper;
import org.og.fmall.user.api.dto.MemberDto;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.dto.MemberResponse;

/**
 * @author:ougen
 * @date:2019/9/2915:38
 */
public interface IMemberCoreService {

    /**
     * 注册用户
     * @param memberRequest
     * @return
     */
    MemberResponse registMember(MemberRequest memberRequest);

    /**
     * 用户登录
     * @param memberRequest
     * @return
     */
    MemberResponse login(MemberRequest memberRequest);

    /**
     * 注销用户
     * @param request
     * @return
     */
    MemberResponse logout(MemberRequest request);

    /**
     * 修改信息
     * @param memberDto
     * @return
     */
    MemberResponse updateMember(MemberDto memberDto);

    /**
     * 修改密码
     * @param request
     * @return
     */
    MemberResponse updatePassword(MemberRequest request);

    /**
     * 游客登陆接口
     * @return
     */
    MemberResponse visitorLogin();
}
