package org.og.fmall.fmalluser.handler.address;

import org.apache.commons.lang3.StringUtils;
import org.og.fmall.commonapi.bussiness.handler.InvokeHandler;
import org.og.fmall.commonapi.dto.RequestParam;
import org.og.fmall.commonapi.dto.ResponseContext;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.fmalluser.annotations.Belong;
import org.og.fmall.fmalluser.factory.address.AddAddressFactory;
import org.og.fmall.user.api.dto.AddressRequest;
import org.og.fmall.user.api.dto.AddressResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author:ougen
 * @date:2019/10/315:04
 */
@Belong(AddAddressFactory.class)
@Component
@Order(1)
public class ParamValidateHandler implements InvokeHandler{

    @Override
    public void handle(RequestParam requestParam, ResponseContext context) {
        AddressRequest request = (AddressRequest) requestParam;
        AddressResponse response = (AddressResponse) context;
        String msg = checked(request);
        if (msg != null){
            response.setCode(CommonEnum.PARAMERTER_WRONG.getCode());
            response.setMsg(msg);
            return;
        }
    }

    private String checked(AddressRequest request) {
        if (request.getTel() == null){
            return "收货人手机号不能为空";
        }
        if(StringUtils.isBlank(request.getReciveName())){
            return "收货人姓名不能为空";
        }
        if (StringUtils.isBlank(request.getProv())){
            return "省份不能为空";
        }
        if (StringUtils.isBlank(request.getCity())){
            return "城市不能为空";
        }
        if (StringUtils.isBlank(request.getStreeName())){
            return "街道不能为空";
        }
        return null;
    }
}
