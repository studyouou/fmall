package org.og.fmall.fmalluser.factory.member;

import org.og.fmall.commonapi.bussiness.factory.pipeline.AbstractPipeLineFactory;
import org.og.fmall.commonapi.bussiness.invoke.PipeLineOutInvoke;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.fmalluser.invoke.member.MemberLoginInvoke;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.dto.MemberResponse;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/9/3015:03
 */
@Component
public class LoginPipeLineFactory extends AbstractPipeLineFactory {
    @Override
    protected PipeLineOutInvoke createOutInvoke(RequestParam requestParam) {
        MemberRequest request = (MemberRequest) requestParam;
        MemberResponse response = new MemberResponse();
        return new MemberLoginInvoke(request,response);
    }
}
