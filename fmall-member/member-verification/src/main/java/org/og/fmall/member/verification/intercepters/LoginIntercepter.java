package org.og.fmall.member.verification.intercepters;

import org.apache.commons.lang3.StringUtils;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.JSONUtil;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.member.verification.annotation.AccessKey;
import org.og.fmall.member.verification.context.MemberSessionContext;
import org.og.fmall.user.api.session.MemberSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author:ougen
 * @date:2019/9/3023:13
 */
public class LoginIntercepter implements HandlerInterceptor{

    @Value("${redis.token.timeout:360}")
    private Integer timeout;

    @Value("${cookie.domain:localhost}")
    private String domain;

    @Autowired
    private RedisService redisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        
        if (handler instanceof HandlerMethod){
            MemberSession memberSession = getMember(request,response);
            MemberSessionContext.setMemberSession(memberSession);
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //Class<?> beanType = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            AccessKey accessKey = method.getAnnotation(AccessKey.class);
            if (accessKey == null){
                return true;
            }
            if (!accessKey.needLogin()){
                return true;
            }
            if (memberSession != null){
                int limit = accessKey.accessLimit();
                if (limit == 0){
                    return true;
                }
                //计算限制次数
                String requestURI = request.getRequestURI();
                Long tel = memberSession.getTel();
                boolean b = limitCountCaculator(requestURI, tel, accessKey);
                if (!b){
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("您操作次数过于频繁，请稍后操作");
                    return b;
                }
                return b;
            }
            response.setContentType("application/json;charset=UTF-8");
            Result result = ResultUtil.buildError();
            result.setCode(CommonEnum.MEMBER_NOT_LOGIN.getCode());
            result.setMsg(CommonEnum.MEMBER_NOT_LOGIN.getMsg());
            response.getWriter().write(JSONUtil.beanToString(result));
            return false;
        }
        return true;
    }

    private boolean limitCountCaculator(String requestURI,Long tel,AccessKey accessKey) {
        String countKey = requestURI+tel;
        String s = redisService.get(countKey);
        int limit = accessKey.accessLimit();
        if (StringUtils.isEmpty(s)){
            redisService.set(countKey,limit-1,accessKey.maxTime());
            return true;
        }
        int count = Integer.parseInt(s);
        long expireTime = redisService.getExpireTime(countKey);
        expireTime = accessKey.maxTime() - expireTime;
        int each = accessKey.maxTime()/limit;
        int ie = (int) expireTime;
        int add = ie/each;
        int mod = ie%each;
        count = add + count > limit ? limit : add + count;

        if (count <= 0){
            return false;
        }
        redisService.set(countKey,count-1,accessKey.maxTime()-mod);
        return true;
    }

    private MemberSession getMember(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        for (Cookie cookie : cookies){
            if (OrderConstants.COOKIE_NAME_LOIN.equals(cookie.getName())){
                String token = cookie.getValue();
                String s = redisService.get(OrderConstants.LOGIN_KEY + token);
                if (StringUtils.isNotEmpty(s)){
                    MemberSession memberSession = JSONUtil.stringToBean(s,MemberSession.class);
                    addAndUpdateCookie(response,token,memberSession);
                    memberSession.setUuid(token);
                    return memberSession;
                }
            }
        }
        return null;
    }

    private void addAndUpdateCookie(HttpServletResponse response, String token, MemberSession memberSession) {
        redisService.set(OrderConstants.LOGIN_KEY+token,memberSession,timeout);
        Cookie cookie = new Cookie(OrderConstants.COOKIE_NAME_LOIN,token);
        cookie.setPath("/");
        cookie.setDomain(domain);
        cookie.setMaxAge(timeout);
        response.addCookie(cookie);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MemberSessionContext.deleteMemberSession();
    }
}
