package org.og.fmall.fmallstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.exception.BaseException;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.fmallstock.mapper.FruitMapper;
import org.og.fmall.fmallstock.model.Fruit;
import org.og.fmall.stock.api.dto.FruitDto;
import org.og.fmall.stock.api.dto.FruitRequest;
import org.og.fmall.stock.api.dto.FruitResponse;
import org.og.fmall.stock.api.enums.StockEnumType;
import org.og.fmall.stock.api.iservice.IFruitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:ougen
 * @date:2019/9/2811:58
 */
@Service
public class FruitService implements IFruitService{
    private static Logger logger = LoggerFactory.getLogger(FruitService.class);
    @Autowired
    private FruitMapper fruitMapper;

    @Override
    public FruitResponse reduceOrderPrepare(Long id, Integer num) {
        System.out.println("--------------------------------执行stocks的reduceOrderPrepare操作-------------------------------------------");
        Integer reduce = fruitMapper.reduceNumById(id, num);
        FruitResponse response = new FruitResponse();
        response.setId(id);
        if (reduce == null ){
            throw new BaseException(CommonEnum.NUM_NOT_ENGHTH.getCode(),CommonEnum.NUM_NOT_ENGHTH.getMsg());
        }
        response.setReduceStock(num);
        response.setId(id);
        return response;
    }

    @Override
    public boolean reduceCommit(BusinessActionContext actionContext) {
        System.out.println("--------------------------------执行stocks的commit操作-------------------------------------------");
        return true;
    }

    @Override
    public boolean reduceRollback(BusinessActionContext actionContext) {
        System.out.println("--------------------------------执行stocks的reduceRollback操作-------------------------------------------");
        Long fruitId = Integer.toUnsignedLong((Integer) actionContext.getActionContext("fruitId"));
        Integer freezeNum = (Integer) actionContext.getActionContext("freezeNum");
        fruitMapper.incrementNumById(fruitId, freezeNum);
        return true;
    }

    @Override
    public FruitResponse incrementNum(long id, int num) {
        Result<FruitResponse> result ;
        fruitMapper.incrementNumById(id,num);
        FruitResponse response = new FruitResponse();
        response.setIncrementStock(num);
        response.setId(id);
        return response;
    }

    @Override
    public FruitResponse addFruit(FruitRequest fruitRequest) {
        Fruit fruit = new Fruit();
        BeanUtils.copyProperties(fruitRequest,fruit);
        fruitMapper.insertSelective(fruit);
        FruitResponse response = new FruitResponse();
        return response;
    }

    @Override
    public FruitResponse deleteFruit(long id) {
        Integer i = fruitMapper.deleteByPrimaryKey(id);
        Result<FruitResponse> result ;
        if (i == 0){
            throw new BaseException(StockEnumType.FRUIT_NOT_FOUND.getCode(),StockEnumType.FRUIT_NOT_FOUND.getMsg());
        }
        FruitResponse response = new FruitResponse();
        response.setId(id);
        return response;
    }
}
