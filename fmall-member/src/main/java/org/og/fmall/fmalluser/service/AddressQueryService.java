package org.og.fmall.fmalluser.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.og.fmall.fmalluser.mapper.AddressMapper;
import org.og.fmall.fmalluser.model.Address;
import org.og.fmall.user.api.dto.AddressDto;
import org.og.fmall.user.api.iservice.IAddressQueryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:ougen
 * @date:2019/9/300:05
 */
@Service
public class AddressQueryService implements IAddressQueryService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public AddressDto queryAddressMemberDefault(Long memberId) {
        Address address = addressMapper.queryAddressMemberDefault(memberId);
        if (address == null){
            return null;
        }
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address,addressDto);
        return addressDto;
    }

    @Override
    public PageInfo<AddressDto> queryAddress(Long memberId,Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Address> addresses = addressMapper.queryAddress(memberId);
        PageInfo<Address> pageInfo = new PageInfo<>(addresses);
        if (addresses == null || addresses.size() == 0){
            return null;
        }
        List<AddressDto> addressDtos = new ArrayList<>();
        for (Address addr : addresses){
            AddressDto addressDto = new AddressDto();
            BeanUtils.copyProperties(addr,addressDto);
            addressDtos.add(addressDto);
        }
        PageInfo<AddressDto> addressDtoPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo,addressDtoPageInfo);
        addressDtoPageInfo.setList(addressDtos);
        return addressDtoPageInfo;
    }

    @Override
    public AddressDto queryAddressById(Long id) {
        Address address = addressMapper.selectByPrimaryKey(id);
        if (address == null){
            return null;
        }
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address,addressDto);
        return addressDto;
    }
}
