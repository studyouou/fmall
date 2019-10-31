package org.og.fmall.fmallshop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import javafx.scene.layout.Pane;
import org.apache.commons.lang3.StringUtils;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.JSONUtil;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.fmallshop.vo.PanelVo;
import org.og.fmall.shopping.api.dto.PanelDto;
import org.og.fmall.shopping.api.service.IHomeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:ougen
 * @date:2019/10/421:03
 * @discreption 用于主页中一些需要的数据查询
 */
@RestController
public class HomePageController {

    @Autowired
    private RedisService redisService;

    @Reference(check = false)
    private IHomeService iHomeService;

    @GetMapping("/home/queryTopPanel")
    public Result<List<PanelVo>> queryPanel(){
        String panel = redisService.get(OrderConstants.TOP_PANEL_KEY);
        Result<List<PanelVo>> result = ResultUtil.build();
        List<PanelVo> panelVos = new ArrayList<>();

        if (StringUtils.isNotBlank(panel)){
            panelVos = JSONUtil.stringToList(panel,PanelVo.class);
        }else {
            List<PanelDto> panelDtos = iHomeService.queryTopPanel();
            for (PanelDto panelDto : panelDtos){
                PanelVo panelVo = new PanelVo();
                BeanUtils.copyProperties(panelDto,panelVo);
                panelVos.add(panelVo);
            }
            redisService.set(OrderConstants.TOP_PANEL_KEY,JSONUtil.beanToString(panelVos));
        }
        result.setData(panelVos);
        return result;
    }

    @GetMapping("/home/queryRecommend")
    public Result<List<PanelVo>> queryRecommend(){
        String panel = redisService.get(OrderConstants.SHOP_RECOMMEND_KEY);
        Result<List<PanelVo>> result = ResultUtil.build();
        List<PanelVo> panelVos = new ArrayList<>();

        if (StringUtils.isNotBlank(panel)){
            panelVos = JSONUtil.stringToList(panel,PanelVo.class);
        }else {
            List<PanelDto> panelDtos = iHomeService.queryRecommend();
            for (PanelDto panelDto : panelDtos){
                PanelVo panelVo = new PanelVo();
                BeanUtils.copyProperties(panelDto,panelVo);
                panelVos.add(panelVo);
            }
            redisService.set(OrderConstants.SHOP_RECOMMEND_KEY,JSONUtil.beanToString(panelVos));
        }
        result.setData(panelVos);
        return result;
    }

    @GetMapping("/home/everyNew")
    public Result<List<PanelVo>> erveryNew(){
        String panel = redisService.get(OrderConstants.BOTTOM_PANEL_KEY);
        Result<List<PanelVo>> result = ResultUtil.build();
        List<PanelVo> panelVos = new ArrayList<>();

        if (StringUtils.isNotBlank(panel)){
            panelVos = JSONUtil.stringToList(panel,PanelVo.class);
        }else {
            List<PanelDto> panelDtos = iHomeService.queryEveryNew();
            for (PanelDto panelDto : panelDtos){
                PanelVo panelVo = new PanelVo();
                BeanUtils.copyProperties(panelDto,panelVo);
                panelVos.add(panelVo);
            }
            redisService.set(OrderConstants.BOTTOM_PANEL_KEY,JSONUtil.beanToString(panelVos));
        }
        result.setData(panelVos);
        return result;
    }

    @GetMapping("/home/queryTopPolling")
    public Result<List<PanelVo>> queryTopPolling(){
        String panel = redisService.get(OrderConstants.TOP_POLLING_KEY);
        Result<List<PanelVo>> result = ResultUtil.build();
        List<PanelVo> panelVos = new ArrayList<>();

        if (StringUtils.isNotBlank(panel)){
            panelVos = JSONUtil.stringToList(panel,PanelVo.class);
        }else {
            List<PanelDto> panelDtos = iHomeService.queryTopPolling();
            for (PanelDto panelDto : panelDtos){
                PanelVo panelVo = new PanelVo();
                BeanUtils.copyProperties(panelDto,panelVo);
                panelVos.add(panelVo);
            }
            redisService.set(OrderConstants.TOP_POLLING_KEY,JSONUtil.beanToString(panelVos));
        }
        result.setData(panelVos);
        return result;
    }

    @GetMapping("/home/queryBottomPolling")
    public Result<List<PanelVo>> queryBottomPolling(){
        String panel = redisService.get(OrderConstants.BOTTOM_POLLING_KEY);
        Result<List<PanelVo>> result = ResultUtil.build();
        List<PanelVo> panelVos = new ArrayList<>();

        if (StringUtils.isNotBlank(panel)){
            panelVos = JSONUtil.stringToList(panel,PanelVo.class);
        }else {
            List<PanelDto> panelDtos = iHomeService.queryBottomPolling();
            for (PanelDto panelDto : panelDtos){
                PanelVo panelVo = new PanelVo();
                BeanUtils.copyProperties(panelDto,panelVo);
                panelVos.add(panelVo);
            }
            redisService.set(OrderConstants.BOTTOM_POLLING_KEY,JSONUtil.beanToString(panelVos));
        }
        result.setData(panelVos);
        return result;
    }

}
