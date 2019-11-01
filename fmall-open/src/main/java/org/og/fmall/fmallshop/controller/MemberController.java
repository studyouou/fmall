package org.og.fmall.fmallshop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.codec.digest.DigestUtils;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.commonapi.utils.TelValidateUtil;
import org.og.fmall.member.verification.annotation.AccessKey;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.dto.MemberResponse;
import org.og.fmall.user.api.iservice.IMemberCoreService;
import org.og.fmall.user.api.session.MemberSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:ougen
 * @date:2019/10/115:32
 * @description 用户操作
 */
@RestController()
@RequestMapping("/auth")
public class MemberController {

    @Reference(check = false)
    private IMemberCoreService iMemberCoreService;

    @Value("${cookie.domain:localhost}")
    private String domain;

    @PostMapping("/login")
    public Result<MemberResponse> login(MemberRequest request, HttpServletResponse response){
        MemberResponse memberResponse = iMemberCoreService.login(request);
        if (memberResponse.getCode() == 0){
            //当登录成功后，需要把登录产生redis的uuid储存在用户浏览器的cookie中，cookie的domain
            //由cookie.domain设置，默认为localhost
            String uuid = memberResponse.getUuid();
            Cookie cookie = new Cookie(OrderConstants.COOKIE_NAME_LOIN,uuid);
            cookie.setPath("/");
            cookie.setMaxAge(60000);
            cookie.setDomain(domain);
            response.addCookie(cookie);
        }
        Result<MemberResponse> result = ResultUtil.build();
        result.setCode(memberResponse.getCode());
        result.setMsg(memberResponse.getMsg());
        result.setData(memberResponse);
        return result;
    }

    @PutMapping("/register")
    public Result<MemberResponse> register(MemberRequest memberRequest, HttpServletRequest request,String verifyCode){
        memberRequest.checked();
        TelValidateUtil.isMobile(memberRequest.getTel());
        //点击注册页面时，会调用获取验证码接口，该接口会把产生的验证码
        boolean b = validateVertify(request, verifyCode);
        if (!b){
            Result result = ResultUtil.buildError();
            result.setMsg("验证码错误");
            return result;
        }
        MemberResponse response = iMemberCoreService.registMember(memberRequest);
        Result<MemberResponse> result = ResultUtil.build();
        result.setCode(response.getCode());
        result.setMsg(response.getMsg());
        result.setData(response);
        return result;
    }

    private boolean validateVertify(HttpServletRequest request, String verifyCode) {
        Cookie cookies[] = request.getCookies();
        String salt = null;
        String md5V = null;
        if(cookies == null){
            return false;
        }
        for (Cookie cookie : cookies){
            if ("registerSalt".equals(cookie.getName())){
                salt = cookie.getValue();
            }
            if ("registerValue".equals(cookie.getName())){
                md5V = cookie.getValue();
            }
        }
        String md5Hex = DigestUtils.md5Hex(salt + verifyCode);
        return md5Hex.equals(md5V);
    }

    @PostMapping("/logout")
    @AccessKey
    public Result<MemberResponse> logout(MemberSession session){
        MemberRequest request = new MemberRequest();
        request.setUuid(session.getUuid());
        MemberResponse response = iMemberCoreService.logout(request);
        Result<MemberResponse> result = ResultUtil.build();
        result.setCode(response.getCode());
        result.setMsg(response.getMsg());
        result.setData(response);
        return result;
    }

    @PostMapping("/visitor/login")
    public Result<MemberResponse> visitorLogin(HttpServletResponse response){
        MemberResponse memberResponse = iMemberCoreService.visitorLogin();
        if (memberResponse.getCode() == 0){
            String uuid = memberResponse.getUuid();
            Cookie cookie = new Cookie(OrderConstants.COOKIE_NAME_LOIN,uuid);
            cookie.setPath("/");
            cookie.setMaxAge(60000);
            cookie.setDomain(domain);
            response.addCookie(cookie);
        }
        Result<MemberResponse> result = ResultUtil.build();
        result.setCode(memberResponse.getCode());
        result.setMsg(memberResponse.getMsg());
        result.setData(memberResponse);
        return result;
    }

    @GetMapping("/isVisitor")
    public Result isVisitor(MemberSession memberSession){
        Result result = ResultUtil.build();
        if (memberSession.getId() == 0L){
            return result;
        }
        result.setCode(CommonEnum.IS_VISITOR.getCode());
        return result;
    }
}
