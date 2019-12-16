package org.og.fmall.fmalluser.handler.address;

import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.fmalluser.annotations.Belong;
import org.og.fmall.fmalluser.factory.address.AddAddressFactory;
import org.og.fmall.fmalluser.mapper.AddressMapper;
import org.og.fmall.fmalluser.model.Address;
import org.og.fmall.user.api.dto.AddressRequest;
import org.og.fmall.user.api.dto.AddressResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/10/315:20
 */
@Component
@Order(3)
@Belong(AddAddressFactory.class)
public class AddAdressHandler implements InvokeHandler{
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public void handle(RequestParam requestParam, ResponseContext context) {
        AddressRequest request = (AddressRequest) requestParam;
        AddressResponse addressResponse = (AddressResponse) context;
        Address address = new Address();
        BeanUtils.copyProperties(request,address);
        addressMapper.inssrtUGeneratedKeys(address);
        addressResponse.setId(address.getId());
        addressResponse.setMemberId(request.getMemberId());
    }
}
