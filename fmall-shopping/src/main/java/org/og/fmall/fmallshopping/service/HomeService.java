package org.og.fmall.fmallshopping.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.og.fmall.fmallshopping.mapper.HomeMapper;
import org.og.fmall.fmallshopping.model.Panel;
import org.og.fmall.shopping.api.dto.PanelDto;
import org.og.fmall.shopping.api.service.IHomeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:ougen
 * @date:2019/10/422:13
 */
@Service
public class HomeService implements IHomeService{

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public List<PanelDto> queryTopPanel() {
        List<Panel> panels = homeMapper.queryPanels(2,3);
        //前三个为系统默认
        List<Panel> defaultPanels = null;
        List<PanelDto> panelDtos = new ArrayList<>();
        return handleList(panels,defaultPanels,panelDtos,2,3);
    }


    @Override
    public List<PanelDto> queryEveryNew() {
        List<Panel> panels = homeMapper.queryPanels(3,4);
        List<Panel> defaultPanels = null;
        List<PanelDto> panelDtos = new ArrayList<>();
        return handleList(panels,defaultPanels,panelDtos,3,4);
    }

    @Override
    public List<PanelDto> queryTopPolling() {
        List<Panel> panels = homeMapper.queryPanels(0,3);
        List<Panel> defaultPanels = null;
        List<PanelDto> panelDtos = new ArrayList<>();
        return handleList(panels,defaultPanels,panelDtos,0,3);
    }

    @Override
    public List<PanelDto> queryBottomPolling() {
        List<Panel> panels = homeMapper.queryPanels(1,3);
        List<Panel> defaultPanels = null;
        List<PanelDto> panelDtos = new ArrayList<>();
        return handleList(panels,defaultPanels,panelDtos,1,3);
    }

    @Override
    public List<PanelDto> queryRecommend() {
        List<Panel> panels = homeMapper.queryPanels(4,6);
        List<Panel> defaultPanels = null;
        List<PanelDto> panelDtos = new ArrayList<>();
        return handleList(panels,defaultPanels,panelDtos,4,6);
    }

    private List<PanelDto> handleList(List<Panel> panels, List<Panel> defaultPanels, List<PanelDto> panelDtos, int location,int offset) {
        if (panels == null){
            defaultPanels = homeMapper.queryPanels(location,offset);
        }
        if (panels.size() < offset){
            defaultPanels = homeMapper.queryPanels(location,offset);
        }
        for (int i = 0; panels.size() < offset; i++){
            panels.add(defaultPanels.get(i));
        }
        for (Panel panel : panels){
            PanelDto panelDto = new PanelDto();
            BeanUtils.copyProperties(panel,panelDto);
            panelDtos.add(panelDto);
        }
        return panelDtos;
    }
}
