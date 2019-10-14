package org.og.fmall.fmalluser.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.og.fmall.fmalluser.mapper.MemberMapper;
import org.og.fmall.fmalluser.model.Member;
import org.og.fmall.user.api.dto.MemberDto;
import org.og.fmall.user.api.iservice.IMemberQueryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author:ougen
 * @date:2019/9/300:37
 */
@Service
public class MemberQueryService implements IMemberQueryService{

    @Autowired
    private MemberMapper memberMapper;
    @Override
    public MemberDto queryMemerByTel(Long tel) {
        Member member = memberMapper.queryMemerByTel(tel);
        if (member == null){
            return null;
        }
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member,memberDto);
        return memberDto;
    }

    @Override
    public MemberDto queryMemberByEmail(String email) {
        Member member = memberMapper.queryMemberByEmail(email);
        if (member == null){
            return null;
        }
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member,memberDto);
        return memberDto;
    }

    @Override
    public MemberDto queryMemberById(Long id) {
        Member member = memberMapper.selectByPrimaryKey(id);
        if (member == null){
            return null;
        }
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member,memberDto);
        return memberDto;
    }
}
