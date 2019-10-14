package org.og.fmall.commonapi.bussiness.invoke;

import org.og.fmall.commonapi.dto.ResponseContext;

/**
 * @author:ougen
 * @date:2019/9/2513:08
 */
public interface OutInvoke {
    //启动流程
    void start();

    ResponseContext getResult();
}
