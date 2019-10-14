package org.og.fmall.user.api.iservice;

import com.github.pagehelper.PageInfo;
import org.og.fmall.user.api.dto.AddressDto;

import java.util.List;

/**
 * @author:ougen
 * @date:2019/9/2920:06
 */
public interface IAddressQueryService {
    /**
     * 查询用户默认地址
     * @param memberId
     * @return
     */
    AddressDto queryAddressMemberDefault(Long memberId);

    /**
     * 查询用户所有收货地址,默认地址第一个，其他顺序按创建时间排序
     * @param memberId
     * @return
     */
    PageInfo<AddressDto> queryAddress(Long memberId, Integer page, Integer pageSize);

    /**
     * 查询
     * @param id
     * @return
     */
    AddressDto queryAddressById(Long id);

}
