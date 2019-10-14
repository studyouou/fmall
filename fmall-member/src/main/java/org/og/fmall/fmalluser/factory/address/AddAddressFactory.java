package org.og.fmall.fmalluser.factory.address;

import org.og.fmall.commonapi.bussiness.factory.pipeline.AbstractPipeLineFactory;
import org.og.fmall.commonapi.bussiness.invoke.PipeLineOutInvoke;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.fmalluser.invoke.address.AddAddressInvoke;
import org.og.fmall.user.api.dto.AddressRequest;
import org.og.fmall.user.api.dto.AddressResponse;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/10/315:05
 */
@Component
public class AddAddressFactory extends AbstractPipeLineFactory {
    @Override
    protected PipeLineOutInvoke createOutInvoke(RequestParam requestParam) {
        AddressRequest request = (AddressRequest) requestParam;
        AddressResponse response = new AddressResponse();
        return new AddAddressInvoke(request,response);
    }
}
