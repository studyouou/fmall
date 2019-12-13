package org.og.fmall.fmallshop.config;

import org.og.fmall.member.verification.context.MemberSessionContext;
import org.og.fmall.user.api.session.MemberSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author:ougen
 * @date:2019/10/216:11
 */

public class MemberSessionParamterResovler implements HandlerMethodArgumentResolver{

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return MemberSession.class == parameter.getParameterType();
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        MemberSession memberSession = MemberSessionContext.getMemberSession();
        System.out.println(memberSession);
        return memberSession;
    }
}
