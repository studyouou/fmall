package org.og.fmall.commonapi.bussiness.handler;


import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;

/**
 * @author:ougen
 * @date:2019/9/2513:43
 */
public interface InvokeHandler {
    void handle(RequestParam requestParam, ResponseContext context);
}
