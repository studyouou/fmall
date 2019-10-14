package org.og.fmall.fmalluser.handler.member;

import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.fmalluser.annotations.Belong;
import org.og.fmall.fmalluser.factory.member.LoginPipeLineFactory;
import org.og.fmall.fmalluser.mapper.MemberMapper;
import org.og.fmall.fmalluser.model.Member;
import org.og.fmall.fmalluser.util.MD5Util;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.dto.MemberResponse;
import org.og.fmall.user.api.session.MemberSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author:ougen
 * @date:2019/9/3013:28
 */
@Component
@Belong(LoginPipeLineFactory.class)
@Order(1)
public class LoginHandler implements InvokeHandler {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void handle(RequestParam requestParam, ResponseContext context) {
        MemberRequest request = (MemberRequest) requestParam;
        MemberResponse response = (MemberResponse) context;
        Long tel = request.getTel();
        Member member = memberMapper.queryMemerByTel(tel);
        if (member == null){
            response.setCode(CommonEnum.MEMBER_TEL_WRONG.getCode());
            response.setMsg(CommonEnum.MEMBER_TEL_WRONG.getMsg());
            return;
        }
        @NotNull String password = request.getPassword();
        String mdPass = MD5Util.passToDB(password,member.getSalt());
        String dbPass = member.getPassword();
        if (!dbPass.equals(mdPass)){
            response.setCode(CommonEnum.MEMBER_TEL_WRONG.getCode());
            response.setMsg(CommonEnum.MEMBER_TEL_WRONG.getMsg());
            return;
        }
        String uuid = UUID.randomUUID().toString();
        response.setUuid(uuid);
        MemberSession memberSession = new MemberSession();
        BeanUtils.copyProperties(member,memberSession);
        redisService.set(OrderConstants.LOGIN_KEY+uuid,memberSession,360);
    }
}
