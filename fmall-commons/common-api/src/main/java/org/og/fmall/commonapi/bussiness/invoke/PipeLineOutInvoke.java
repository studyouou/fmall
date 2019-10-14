package org.og.fmall.commonapi.bussiness.invoke;

import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;

/**
 * @author:ougen
 * @date:2019/9/2513:41
 */
public interface PipeLineOutInvoke extends OutInvoke {
    void addFirst(InvokeHandler invokeHandler);

    void addLast(InvokeHandler invokeHandler);
}
