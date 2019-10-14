package org.og.fmall.fmalluser.mapper;

import org.apache.ibatis.annotations.Param;
import org.og.fmall.commonapi.tkmapper.TkMapper;
import org.og.fmall.fmalluser.model.Address;

import java.util.List;

/**
 * @author:ougen
 * @date:2019/9/2922:27
 */
public interface AddressMapper extends TkMapper<Address> {
    Address queryAddressMemberDefault(@Param("memberId") Long memberId);

    List<Address> queryAddress(@Param("memberId") Long memberId);

    int updateDefaultAddress(@Param("id")Long id);

    int cancelDefaultAddress(@Param("memberId")Long memberId);

    long inssrtUGeneratedKeys(@Param("address")Address address);
}
