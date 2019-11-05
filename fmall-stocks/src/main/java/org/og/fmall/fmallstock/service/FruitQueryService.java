package org.og.fmall.fmallstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.og.fmall.commonapi.exception.BaseException;
import org.og.fmall.fmallstock.mapper.FruitMapper;
import org.og.fmall.fmallstock.model.Fruit;
import org.og.fmall.stock.api.dto.FruitDto;
import org.og.fmall.stock.api.enums.StockEnumType;
import org.og.fmall.stock.api.iservice.IFruitQueryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author:ougen
 * @date:2019/9/2815:51
 */
@Service
public class FruitQueryService implements IFruitQueryService {
    @Autowired
    private FruitMapper fruitMapper;

    @Override
    public Integer queryLeaveNum(long id) {
         Integer stock = fruitMapper.queryStock(id);
         if (stock == null){
             throw new BaseException(StockEnumType.FRUIT_NOT_FOUND.getCode(),StockEnumType.FRUIT_NOT_FOUND.getMsg());
         }
         return stock;
    }

    @Override
    public FruitDto queryFruitById(long id) {
        Fruit fruit = fruitMapper.selectByPrimaryKey(id);
        if (fruit ==  null){
            throw new BaseException(StockEnumType.FRUIT_NOT_FOUND.getCode(),StockEnumType.FRUIT_NOT_FOUND.getMsg());
        }
        FruitDto fruitDto = new FruitDto();
        BeanUtils.copyProperties(fruit,fruitDto);
        return fruitDto;
    }

    @Override
    public List<FruitDto> queryAllFruit() {
        List<Fruit> fruits = fruitMapper.selectAll();
        if (fruits == null || fruits.size() == 0){
            return null;
        }
        List<FruitDto> list = new ArrayList<>(fruits.size());
        fruits.forEach((fruit -> {
            FruitDto fruitDto = new FruitDto();
            BeanUtils.copyProperties(fruit,fruitDto);
            list.add(fruitDto);
        }));
        return list;
    }
}
