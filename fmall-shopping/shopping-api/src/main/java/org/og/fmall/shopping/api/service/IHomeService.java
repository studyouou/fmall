package org.og.fmall.shopping.api.service;

import org.og.fmall.shopping.api.dto.PanelDto;

import java.util.List;

/**
 * @author:ougen
 * @date:2019/10/421:56
 */
public interface IHomeService {

    List<PanelDto> queryTopPanel();

    List<PanelDto> queryEveryNew();

    List<PanelDto> queryTopPolling();

    List<PanelDto> queryBottomPolling();

    List<PanelDto> queryRecommend();

}
