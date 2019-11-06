package org.og.fmall.fmalluser;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.og.fmall.fmalluser.model.Address;
import org.og.fmall.user.api.dto.AddressDto;
import org.og.fmall.user.api.dto.AddressRequest;
import org.og.fmall.user.api.dto.AddressResponse;
import org.og.fmall.user.api.iservice.IAddressQueryService;
import org.og.fmall.user.api.iservice.IAddressService;

import java.util.List;

/**
 * @author:ougen
 * @date:2019/10/315:02
 */
@Slf4j
public class AdressTest extends FmallUserApplicationTests {

    @Reference(check = true)
    private IAddressService iAddressService;

    @Reference(check = true)
    private IAddressQueryService iAddressQueryService;

    @Test
    public void testAddAddress(){
        AddressRequest request = new AddressRequest();
        request.setMemberId(2L);
        request.setProv("四川省");
        request.setReciveName("欧根");
        request.setTel(13981973370L);
        request.setArea("成华区");
        request.setCity("成都市");
        request.setStreeName("成都理工大学银杏园362寝室");
        AddressResponse response = iAddressService.addAddress(request);
        Assert.assertEquals(response.getMsg(),0,response.getCode());
        request.setDefaultAddress(1);
        request.setId(response.getId());
        iAddressService.updateAddress(request);
        log.info("addAddress={}",response);
    }


    @Test
    public void testDeleteAddress(){
        AddressResponse response = iAddressService.deleteAddress(15l);
        Assert.assertEquals(response.getMsg(),0,response.getCode());
    }

    @Test
    public void testQueryAddressById(){
        AddressDto addressDto = iAddressQueryService.queryAddressById(1L);
        Assert.assertNotNull(addressDto);
    }


    @Test
    public void testQueryAddressMemberDefault(){
        AddressDto addressDto = iAddressQueryService.queryAddressMemberDefault(2L);
        log.info("queryAddressMemberDefault={}",addressDto);
    }
}
