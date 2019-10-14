package org.og.fmall.commonapi.bussiness.invoke;

import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;

import java.util.LinkedList;

/**
 * @author:ougen
 * @date:2019/9/2513:45
 */
public abstract class AbstractPipeLineOutInvoke implements PipeLineOutInvoke {

    private final LinkedList<InvokeHandler> handlers = new LinkedList<>();

    private RequestParam requestParam;

    private ResponseContext responseContext;

    public AbstractPipeLineOutInvoke(RequestParam requestParam,ResponseContext responseContext){
        this.requestParam = requestParam;
        this.responseContext = responseContext;
    }

    @Override
    public void addFirst(InvokeHandler invokeHandler) {
        handlers.addFirst(invokeHandler);
    }

    @Override
    public void addLast(InvokeHandler invokeHandler) {
        handlers.addLast(invokeHandler);
    }

    @Override
    public void start() {
        for (InvokeHandler invokeHandler : handlers){
            if (responseContext.getCode() != 0){
                return;
            }
            invokeHandler.handle(requestParam,responseContext);
        }
    }

    @Override
    public ResponseContext getResult() {
        return this.responseContext;
    }
}
