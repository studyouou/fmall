package org.og.fmall.fmalluser.invoke.member;

import org.og.fmall.commonapi.bussiness.invoke.AbstractPipeLineOutInvoke;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.dto.MemberResponse;

/**
 * @author:ougen
 * @date:2019/9/3015:01
 */
public class MemberLoginInvoke extends AbstractPipeLineOutInvoke {
    public MemberLoginInvoke(MemberRequest requestParam, MemberResponse responseContext) {
        super(requestParam, responseContext);
    }
}
