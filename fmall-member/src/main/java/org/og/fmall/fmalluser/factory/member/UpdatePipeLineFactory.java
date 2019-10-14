package org.og.fmall.fmalluser.factory.member;

import org.og.fmall.commonapi.bussiness.factory.pipeline.AbstractPipeLineFactory;
import org.og.fmall.commonapi.bussiness.invoke.PipeLineOutInvoke;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.fmalluser.invoke.member.UpdatePasswordInvoke;
import org.og.fmall.user.api.dto.MemberRequest;
import org.og.fmall.user.api.dto.MemberResponse;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/9/3015:12
 */
@Component
public class UpdatePipeLineFactory extends AbstractPipeLineFactory {
    @Override
    protected PipeLineOutInvoke createOutInvoke(RequestParam requestParam) {
        MemberRequest request = (MemberRequest) requestParam;
        MemberResponse response = new MemberResponse();
        return new UpdatePasswordInvoke(request,response);
    }
}
