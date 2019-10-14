package org.og.fmall.fmallshop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.fmallshop.vo.AddressVo;
import org.og.fmall.member.verification.annotation.AccessKey;
import org.og.fmall.user.api.dto.AddressDto;
import org.og.fmall.user.api.dto.AddressRequest;
import org.og.fmall.user.api.dto.AddressResponse;
import org.og.fmall.user.api.iservice.IAddressQueryService;
import org.og.fmall.user.api.iservice.IAddressService;
import org.og.fmall.user.api.session.MemberSession;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:ougen
 * @date:2019/10/220:07
 */
@RestController
public class AddressController {

    @Reference(check = false)
    private IAddressQueryService iAddressQueryService;

    @Reference(check = false)
    private IAddressService iAddressService;

    @GetMapping("/addresses")
    @AccessKey(accessLimit = 20)
    public Result<PageInfo<AddressVo>> queryAddresses(MemberSession memberSession,int page,int pageSize){
        Result<PageInfo<AddressVo>> result = ResultUtil.build();
        if (page == 0){
            page = 1;
        }
        if (pageSize == 0){
            pageSize = 10;
        }
        PageInfo<AddressDto> addressDtos = iAddressQueryService.queryAddress(memberSession.getId(),page,pageSize);
        if (addressDtos == null || addressDtos.getList() == null || addressDtos.getList().size() == 0){
            return result;
        }
        List<AddressVo> addressVoList = new ArrayList<>();
        for (AddressDto dto : addressDtos.getList()){
            AddressVo vo = new AddressVo();
            BeanUtils.copyProperties(dto,vo);
            addressVoList.add(vo);
        }
        PageInfo<AddressVo> pageInfo = new PageInfo<>();
        BeanUtils.copyProperties(addressDtos,pageInfo);
        pageInfo.setList(addressVoList);
        result.setData(pageInfo);
        return result;
    }

    @GetMapping("/{id}/adress")
    @AccessKey(accessLimit = 20)
    public Result<AddressVo> queryAddress(Long id){
        Result<AddressVo> result = ResultUtil.build();
        if (id == null){
            result.setCode(CommonEnum.PARAMERTER_WRONG.getCode());
            result.setMsg(CommonEnum.PARAMERTER_WRONG.getMsg());
            return result;
        }
        AddressDto addressDto = iAddressQueryService.queryAddressById(id);
        if (addressDto == null){
            result.setCode(CommonEnum.ADDRESS_NOT_FOUND.getCode());
            result.setMsg(CommonEnum.ADDRESS_NOT_FOUND.getMsg());
            return result;
        }
        AddressVo addressVo = new AddressVo();
        BeanUtils.copyProperties(addressDto,addressVo);
        result.setData(addressVo);
        return result;
    }
    @GetMapping("/default/address")
    @AccessKey(accessLimit = 20)
    public Result<AddressVo> queryDefaultAddress(MemberSession memberSession){
        Result<AddressVo> result = ResultUtil.build();
        AddressDto addressDto = iAddressQueryService.queryAddressMemberDefault(memberSession.getId());
        if (addressDto == null){
            return result;
        }
        AddressVo addressVo = new AddressVo();
        BeanUtils.copyProperties(addressDto,addressVo);
        result.setData(addressVo);
        return result;
    }
    @PostMapping("/address")
    @AccessKey
    public Result<AddressResponse> insertAddress(@Valid AddressRequest request, BindingResult bindingResult,MemberSession session){
        Result<AddressResponse> result = ResultUtil.build();
        if (bindingResult.hasErrors()){
            result.setCode(CommonEnum.PARAMERTER_WRONG.getCode());
            result.setMsg(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        request.setMemberId(session.getId());
        AddressResponse response = iAddressService.addAddress(request);
        result.setCode(response.getCode());
        result.setMsg(response.getMsg());
        result.setData(response);
        return result;
    }

    @DeleteMapping("/{id}/address")
    @AccessKey
    public Result<AddressResponse> deleteAddress(@PathVariable("id") Long id){
        Result<AddressResponse> result = ResultUtil.build();
        if (id == null){
            result.setCode(CommonEnum.PARAMERTER_WRONG.getCode());
            result.setMsg(CommonEnum.PARAMERTER_WRONG.getMsg());
            return result;
        }
        AddressResponse response = iAddressService.deleteAddress(id);
        result.setCode(response.getCode());
        result.setMsg(result.getMsg());
        result.setData(response);
        return result;
    }

    @PutMapping("/address")
    public Result<AddressResponse> updateAddress(AddressRequest request){
        AddressResponse response = iAddressService.updateAddress(request);
        Result<AddressResponse> result = ResultUtil.build();
        result.setCode(response.getCode());
        result.setMsg(response.getMsg());
        result.setData(response);
        return result;
    }

}
