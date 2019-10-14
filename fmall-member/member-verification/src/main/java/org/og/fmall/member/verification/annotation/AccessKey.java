package org.og.fmall.member.verification.annotation;

import java.lang.annotation.*;

/**
 * @author:ougen
 * @date:2019/9/3023:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessKey {

    boolean needLogin() default true;
    //限制次数
    int accessLimit() default 0;

    //规定时间
    int maxTime() default 60;
}
