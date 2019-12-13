package org.og.fmall.commonapi.annotation;

import java.lang.annotation.*;

/**
 * @author:ougen
 * @date:2019/9/3011:22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Belong {
    Class value();
}
