package org.og.fmall.commonapi.bussiness.factory.pipeline;

import org.og.fmall.commonapi.bussiness.invoke.OutInvoke;
import org.og.fmall.commonapi.dto.RequestParam;

/**
 * @author:ougen
 * @date:2019/9/2513:09
 */
public interface PipeLineFactory<T extends RequestParam> {
    OutInvoke build(T t);
}
