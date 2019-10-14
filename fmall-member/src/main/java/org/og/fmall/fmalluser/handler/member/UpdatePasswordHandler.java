package org.og.fmall.fmalluser.handler.member;

import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.fmalluser.annotations.Belong;
import org.og.fmall.fmalluser.factory.member.UpdatePipeLineFactory;
import org.og.fmall.fmalluser.mapper.MemberMapper;
import org.og.fmall.fmalluser.model.Member;
import org.og.fmall.fmalluser.util.MD5Util;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.dto.MemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/9/3015:11
 */

@Component
@Belong(UpdatePipeLineFactory.class)
@Order(1)
public class UpdatePasswordHandler implements InvokeHandler{
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void handle(RequestParam requestParam, ResponseContext context) {
        MemberRequest request = (MemberRequest) requestParam;
        MemberResponse response = (MemberResponse) context;
        Long id = request.getId();
        String password = request.getPassword();
        String newPassword = request.getNewPassword();
        String rePassword = request.getRePassword();
        if (newPassword.equals(rePassword)){
            response.setCode(CommonEnum.PASSWORD_IS_DIFFIRENT.getCode());
            response.setMsg(CommonEnum.PASSWORD_IS_DIFFIRENT.getMsg());
            return;
        }
        Member member = memberMapper.queryMemerByTel(id);
        if (member == null){
            response.setCode(CommonEnum.MEMBER_TEL_WRONG.getCode());
            response.setMsg(CommonEnum.MEMBER_TEL_WRONG.getMsg());
            return;
        }

        String pass = MD5Util.passToDB(request.getPassword(), member.getSalt());
        String dbPass = member.getPassword();
        if (dbPass.equals(pass)){
            response.setCode(CommonEnum.PASSWORD_IS_WRONG.getCode());
            response.setMsg(CommonEnum.PASSWORD_IS_WRONG.getMsg());
            return;
        }
        String newDbPass = MD5Util.passToDB(newPassword,member.getSalt());
        if (dbPass.equals(newDbPass)){
            response.setCode(CommonEnum.PASSWORD_IS_EQUALS.getCode());
            response.setMsg(CommonEnum.PASSWORD_IS_EQUALS.getMsg());
            return;
        }
        memberMapper.updatePassword(id,newDbPass);
    }
}
