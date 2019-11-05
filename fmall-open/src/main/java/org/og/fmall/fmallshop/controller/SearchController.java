package org.og.fmall.fmallshop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.JSONUtil;
import org.og.fmall.commonapi.utils.Page;
import org.og.fmall.commonapi.utils.PageUtil;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.elasticsearch.api.dto.ElasticSearchDto;
import org.og.fmall.elasticsearch.api.iservices.IElasticSearchService;
import org.og.fmall.fmallshop.vo.FruitVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: og
 * @description:
 * @date: 2019/11/4
 */
@RestController
public class SearchController {

    @Reference(check = false)
    private IElasticSearchService elasticSearchService;

    @GetMapping("/search")
    public Result<Page<FruitVo>> searchFruit(String key,Integer page,Integer pageSize){

        Result<Page<FruitVo>> result = ResultUtil.build();
        if (page == null){
            page = 1;
        }
        if (pageSize == null){
            pageSize = 5;
        }
        int from = (page-1)*pageSize;
        ElasticSearchDto search = elasticSearchService.search(key,pageSize,from);
        if (search.getCode() != 0){
            result.setCode(search.getCode());
            result.setMsg(search.getMsg());
            return result;
        }
        if (search.getData()==null || search.getData().size()==0){
            result.setData(null);
            return result;
        }
        List<String> data = search.getData();
        List<FruitVo> fruitVos = new ArrayList<>();
        for (String d : data){
            FruitVo fruitVo = JSONUtil.stringToBean(d, FruitVo.class);
            fruitVos.add(fruitVo);
        }
        Page<FruitVo> pageInfo = PageUtil.createPage(search.getTotal().intValue(),page,pageSize,fruitVos);

        result.setData(pageInfo);
        return result;
    }
}
