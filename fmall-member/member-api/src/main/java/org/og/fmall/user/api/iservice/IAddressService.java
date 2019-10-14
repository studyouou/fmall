package org.og.fmall.user.api.iservice;

import org.og.fmall.user.api.dto.AddressDto;
import org.og.fmall.user.api.dto.AddressRequest;
import org.og.fmall.user.api.dto.AddressResponse;

/**
 * @author:ougen
 * @date:2019/9/2916:45
 */
public interface IAddressService {
    /**
     * 添加收货地址
     * @param addressRequest
     */
    AddressResponse addAddress(AddressRequest addressRequest);

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    AddressResponse deleteAddress(Long id);

    /**
     * 修改收货地址信息
     * @param addressRequest
     * @return
     */
    AddressResponse updateAddress(AddressRequest addressRequest);


}
