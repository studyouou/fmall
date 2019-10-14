package org.og.fmall.fmallshopping.annotations;

import java.lang.annotation.*;

/**
 * @author:ougen
 * @date:2019/9/3011:22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Belong {
    Class value();
}
