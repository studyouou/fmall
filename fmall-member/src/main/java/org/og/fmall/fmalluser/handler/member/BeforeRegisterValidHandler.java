package org.og.fmall.fmalluser.handler.member;

import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.fmalluser.annotations.Belong;
import org.og.fmall.fmalluser.factory.member.RegiestPipeLineFactory;
import org.og.fmall.fmalluser.mapper.MemberMapper;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.dto.MemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/10/315:47
 */
@Component
@Order(1)
@Belong(RegiestPipeLineFactory.class)
public class BeforeRegisterValidHandler implements InvokeHandler{

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public void handle(RequestParam requestParam, ResponseContext context) {
        MemberRequest request = (MemberRequest) requestParam;
        MemberResponse response = (MemberResponse) context;
        Long tel = memberMapper.hasRegiestTel(request.getTel());
        if (tel != null){
            response.setCode(CommonEnum.MEMBER_HAS_EXIST.getCode());
            response.setMsg(tel+CommonEnum.MEMBER_HAS_EXIST.getMsg());
            return;
        }
    }
}
