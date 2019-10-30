package org.og.fmall.fmallshop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.fmallshop.vo.FruitVo;
import org.og.fmall.stock.api.dto.FruitDto;
import org.og.fmall.stock.api.iservice.IFruitQueryService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author:ougen
 * @date:2019/10/923:06
 */
@RestController
public class StockController {

    @Reference(check = false)
    private IFruitQueryService iFruitQueryService;

    @GetMapping("/{id}/fruit")
    public Result<FruitVo> queryFruit(@PathVariable("id")Long id){
        FruitDto fruitDto = iFruitQueryService.queryFruitById(id);
        Result<FruitVo> result = ResultUtil.build();
        if (fruitDto ==  null){
            result.setCode(CommonEnum.FRUIT_NOT_FOUND.getCode());
            result.setMsg(CommonEnum.FRUIT_NOT_FOUND.getMsg());
            return result;
        }
        FruitVo fruitVo = new FruitVo();
        BeanUtils.copyProperties(fruitDto,fruitVo);
        result.setData(fruitVo);
        return result;
    }

}
