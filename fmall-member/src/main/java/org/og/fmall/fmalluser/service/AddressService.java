package org.og.fmall.fmalluser.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.og.fmall.commonapi.bussiness.invoke.PipeLineOutInvoke;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.fmalluser.factory.address.AddAddressFactory;
import org.og.fmall.fmalluser.mapper.AddressMapper;
import org.og.fmall.fmalluser.model.Address;
import org.og.fmall.user.api.dto.AddressRequest;
import org.og.fmall.user.api.dto.AddressResponse;
import org.og.fmall.user.api.iservice.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:ougen
 * @date:2019/9/2922:12
 */
@Service
public class AddressService implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddAddressFactory addAddressFactory;
    @Override
    public AddressResponse addAddress(AddressRequest addressRequest) {
        PipeLineOutInvoke outInvoke = addAddressFactory.build(addressRequest);
        outInvoke.start();
        AddressResponse result = (AddressResponse) outInvoke.getResult();
        return result;
    }

    @Override
    public AddressResponse deleteAddress(Long id) {
        int i = addressMapper.deleteByPrimaryKey(id);
        AddressResponse response = new AddressResponse();
        response.setId(id);
        if (i<=0){
            response.setCode(CommonEnum.ADDRESS_NOT_FOUND.getCode());
            response.setMsg(CommonEnum.ADDRESS_NOT_FOUND.getMsg());
            return response;
        }
        return response;
    }

    @Override
    @Transactional
    public AddressResponse updateAddress(AddressRequest addressRequest) {
        Address address = new Address();
        BeanUtils.copyProperties(addressRequest,address);
        if (address.getDefaultAddress() != null){
            if (address.getDefaultAddress() == 1){
                addressMapper.cancelDefaultAddress(address.getId());
            }
        }
        addressMapper.updateByPrimaryKeySelective(address);
        AddressResponse response = new AddressResponse();
        response.setId(addressRequest.getId());
        return response;
    }

}
