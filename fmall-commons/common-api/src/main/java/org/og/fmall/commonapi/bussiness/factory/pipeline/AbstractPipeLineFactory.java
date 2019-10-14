package org.og.fmall.commonapi.bussiness.factory.pipeline;

import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.bussiness.invoke.OutInvoke;
import org.og.fmall.commonapi.bussiness.invoke.PipeLineOutInvoke;
import org.og.fmall.commonapi.dto.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Handler;

/**
 * @author:ougen
 * @date:2019/9/2513:11
 */
public abstract class AbstractPipeLineFactory<T extends RequestParam> implements PipeLineFactory<T>{
    private LinkedList<InvokeHandler> invokeHandlers;

    public AbstractPipeLineFactory(){
        this.invokeHandlers = new LinkedList<>();
    }

    @Override
    public PipeLineOutInvoke build(T requestParam) {
        PipeLineOutInvoke outInvoke = createOutInvoke(requestParam);
        addFromListHandler(outInvoke,invokeHandlers);
        andHandler(outInvoke);
        return outInvoke;
    }

    private void addFromListHandler(PipeLineOutInvoke outInvoke, LinkedList<InvokeHandler> invokeHandlers) {
        for (InvokeHandler invokeHandler : invokeHandlers){
            outInvoke.addLast(invokeHandler);
        }
    }

    protected void andHandler(PipeLineOutInvoke outInvoke){

    }

    protected abstract PipeLineOutInvoke createOutInvoke(T requetParam);

    public void addFirst(InvokeHandler invokeHandler){
        invokeHandlers.addFirst(invokeHandler);
    }
    public void addLast(InvokeHandler invokeHandler){
        invokeHandlers.addLast(invokeHandler);
    }
}
