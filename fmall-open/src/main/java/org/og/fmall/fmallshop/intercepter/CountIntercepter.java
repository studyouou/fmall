package org.og.fmall.fmallshop.intercepter;

import org.apache.commons.lang3.StringUtils;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commontools.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ougen
 * @description:
 * @date: 2019/10/31
 */
public class CountIntercepter implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (StringUtils.isNotEmpty(requestURI) && requestURI.contains(".html")){
            redisService.incr(OrderConstants.COUNTHTML);
        }
        return true;
    }
}
