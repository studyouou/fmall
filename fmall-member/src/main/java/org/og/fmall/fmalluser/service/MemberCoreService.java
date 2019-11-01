package org.og.fmall.fmalluser.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.og.fmall.commonapi.bussiness.invoke.PipeLineOutInvoke;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.fmalluser.factory.member.LoginPipeLineFactory;
import org.og.fmall.fmalluser.factory.member.RegiestPipeLineFactory;
import org.og.fmall.fmalluser.mapper.MemberMapper;
import org.og.fmall.fmalluser.model.Member;
import org.og.fmall.fmalluser.util.MD5Util;
import org.og.fmall.user.api.dto.MemberDto;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.dto.MemberResponse;
import org.og.fmall.user.api.iservice.IMemberCoreService;
import org.og.fmall.user.api.session.MemberSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author:ougen
 * @date:2019/9/300:35
 */
@Service
public class MemberCoreService implements IMemberCoreService{

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RegiestPipeLineFactory regiestPipeLineFactory;

    @Autowired
    private LoginPipeLineFactory loginPipeLineFactory;

    @Autowired
    private RedisService redisService;

    @Override
    public MemberResponse registMember(MemberRequest memberRequest) {
        PipeLineOutInvoke outInvoke = regiestPipeLineFactory.build(memberRequest);
        outInvoke.start();
        MemberResponse response = (MemberResponse) outInvoke.getResult();
        return response;
    }

    @Override
    public MemberResponse login(MemberRequest memberRequest) {
        PipeLineOutInvoke outInvoke = loginPipeLineFactory.build(memberRequest);
        outInvoke.start();
        MemberResponse response = (MemberResponse) outInvoke.getResult();
        return response;
    }

    @Override
    public MemberResponse logout(MemberRequest request) {
        String uuid = request.getUuid();
        MemberResponse response = new MemberResponse();
        redisService.del(OrderConstants.LOGIN_KEY + uuid);
        return response;
    }

    @Override
    public MemberResponse updateMember(MemberDto memberDto) {
        Member member = new Member();
        BeanUtils.copyProperties(memberDto,member);
        memberMapper.updateByPrimaryKeySelective(member);
        MemberResponse response = new MemberResponse();
        return response;
    }

    @Override
    public MemberResponse updatePassword(MemberRequest request) {
        String newPassword = request.getNewPassword();
        String toDB = MD5Util.passToDB(newPassword, request.getSalt());
        int i = memberMapper.updatePassword(request.getId(), toDB);
        if (i > 0){
            redisService.del(OrderConstants.LOGIN_KEY+request.getUuid());
        }
        MemberResponse response = new MemberResponse();
        response.setId(request.getId());
        return response;
    }

    @Override
    public MemberResponse visitorLogin() {
        MemberResponse response = new MemberResponse();
        String uuid = UUID.randomUUID().toString();
        response.setUuid(uuid);
        MemberSession memberSession = new MemberSession();
        memberSession.setId(0L);
        memberSession.setNickName("游客模式");
        redisService.set(OrderConstants.LOGIN_KEY+uuid,memberSession,360);
        return response;
    }
}
