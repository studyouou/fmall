package org.og.fmall.fmalluser.handler.member;

import org.apache.commons.lang3.RandomStringUtils;
import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.fmalluser.annotations.Belong;
import org.og.fmall.fmalluser.factory.member.RegiestPipeLineFactory;
import org.og.fmall.fmalluser.mapper.MemberMapper;
import org.og.fmall.fmalluser.model.Member;
import org.og.fmall.fmalluser.util.MD5Util;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.dto.MemberResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author:ougen
 * @date:2019/9/3010:24
 */
@Component
@Belong(RegiestPipeLineFactory.class)
@Order(2)
public class RegiestHandler implements InvokeHandler{
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public void handle(RequestParam requestParam, ResponseContext context) {
        MemberRequest request = (MemberRequest) requestParam;
        MemberResponse response = (MemberResponse) context;
        Member member = new Member();
        String s = RandomStringUtils.random(8,true,true);
        BeanUtils.copyProperties(request,member);
        member.setSalt(s);
        member.setPassword(MD5Util.passToDB(member.getPassword(),member.getSalt()));
        memberMapper.insertSelective(member);
    }
}
