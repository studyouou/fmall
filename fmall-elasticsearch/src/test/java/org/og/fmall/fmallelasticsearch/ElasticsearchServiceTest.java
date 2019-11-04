package org.og.fmall.fmallelasticsearch;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.og.fmall.commonapi.utils.JSONUtil;
import org.og.fmall.elasticsearch.api.dto.ElasticSearchDto;
import org.og.fmall.elasticsearch.api.iservices.IElasticSearchService;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @author: og
 * @description:
 * @date: 2019/11/4
 */
@Slf4j
public class ElasticsearchServiceTest  extends ElasticsearchApplicationTests{

    @Reference
    private IElasticSearchService iElasticSearchService;

    @Test
    public void insert(){
        Fruit fruit = new Fruit();
        fruit.setId(2L);
        fruit.setDisciption("苹果是蔷薇科苹果亚科苹果属植物，其树为落叶乔木。苹果营养价值很高，富含矿物质和维生素，含钙量丰富。");
        fruit.setEachPrice(new BigDecimal("7.50"));
        fruit.setFruitName("龙蛇果");
        fruit.setImgUrl("/images/import_list2.jpg");
        fruit.setSellerId(1L);
        iElasticSearchService.insert("fruits","fruit", null,JSONUtil.beanToString(fruit));
    }

    @Test
    public void search(){
        ElasticSearchDto fruit = iElasticSearchService.search("龙蛇果");
        log.info("fruit={}",fruit);
    }
}
