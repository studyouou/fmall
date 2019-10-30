package org.og.fmall.fmallstock;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.og.fmall.stock.api.dto.FruitDto;
import org.og.fmall.stock.api.dto.FruitRequest;
import org.og.fmall.stock.api.dto.FruitResponse;
import org.og.fmall.stock.api.iservice.IFruitQueryService;
import org.og.fmall.stock.api.iservice.IFruitService;

import java.math.BigDecimal;

/**
 * @author:ougen
 * @date:2019/10/222:08
 */
@Slf4j
public class StocksTest extends FmallStocksTest {

    @Reference(check = false)
    private IFruitQueryService iFruitQueryService;

    @Reference(check = false)
    private IFruitService iFruitService;

    @Test
    public void testqQueryFruitById(){
        FruitDto fruitDto = iFruitQueryService.queryFruitById(1);
        log.info("queryFruitById={}",fruitDto);
    }
    @Test
    public void testQueryLeaveNum(){
        Integer integer = iFruitQueryService.queryLeaveNum(1);
        log.info("queryLeaveNum={}",integer);
    }
    @Test
    public void testIncrementNum(){
        FruitResponse response = iFruitService.incrementNum(1, 10);
        log.info("incrementNum={}",response);
    }

    @Test
    public void testAddFruit(){
        FruitRequest request = new FruitRequest();
        request.setStock(1000);
        request.setFruitName("车厘子");
        request.setImgUrl("/images/4.jpg");
        request.setSellerId(1);
        request.setEachPrice(new BigDecimal("30.50"));
        request.setDisciption("这是樱桃的进化版，好吃的很哦，想不到的美味，你值的拥有");
        FruitResponse response = iFruitService.addFruit(request);
        log.info("addFruit={}",response);
    }
}
