package org.og.fmall.fmallshop.config;

import org.og.fmall.fmallshop.intercepter.CountIntercepter;
import org.og.fmall.member.verification.intercepters.LoginIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * @author:ougen
 * @date:2019/10/115:12
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Bean
    public LoginIntercepter loginIntercepter(){
        return new LoginIntercepter();
    }


    @Bean
    public MemberSessionParamterResovler memberSessionParamterResovler(){
        return new MemberSessionParamterResovler();
    }

    @Bean
    public CountIntercepter countIntercepter(){
        return new CountIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/error");

        registry.addInterceptor(countIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/*.js","/*.css","/*.jpg","/*.png");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberSessionParamterResovler());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**");
    }

}
