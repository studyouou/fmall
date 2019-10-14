package org.og.fmall.fmalluser.factory.member;

import org.og.fmall.commonapi.bussiness.factory.pipeline.AbstractPipeLineFactory;
import org.og.fmall.commonapi.bussiness.invoke.PipeLineOutInvoke;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.fmalluser.invoke.member.MemberRegistInvoke;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.dto.MemberResponse;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/9/309:49
 */
@Component
public class RegiestPipeLineFactory extends AbstractPipeLineFactory {

    @Override
    protected PipeLineOutInvoke createOutInvoke(RequestParam requestParam) {
        MemberResponse response = new MemberResponse();
        MemberRequest request = (MemberRequest) requestParam;
        return new MemberRegistInvoke(request,response);
    }
}
