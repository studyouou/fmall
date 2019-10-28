package org.og.fmall.fmalluser.handler.member;

import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.fmalluser.annotations.Belong;
import org.og.fmall.fmalluser.factory.member.RegiestPipeLineFactory;
import org.og.fmall.fmalluser.mapper.MemberMapper;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.iservice.IMemberCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/10/1414:59
 */
@Component
@Belong(RegiestPipeLineFactory.class)
@Order(5)
public class HasRegisterHander implements InvokeHandler{
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public void handle(RequestParam requestParam, ResponseContext context) {
        MemberRequest memberRequest = (MemberRequest) requestParam;
        Long tel = memberMapper.hasRegiestTel(memberRequest.getTel());
        if (tel!=null){
            context.setCode(CommonEnum.MEMBER_HAS_EXIST.getCode());
            context.setMsg(CommonEnum.MEMBER_HAS_EXIST.getMsg());
            return;
        }
    }
}
