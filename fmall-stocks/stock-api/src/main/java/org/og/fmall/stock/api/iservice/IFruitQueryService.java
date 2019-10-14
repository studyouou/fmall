package org.og.fmall.stock.api.iservice;

import org.og.fmall.commonapi.result.Result;
import org.og.fmall.stock.api.dto.FruitDto;

/**
 * @author:ougen
 * @date:2019/9/2814:05
 */
public interface IFruitQueryService {
    /**
     * 查询水果库存
     * @param id
     * @return
     */
    Integer queryLeaveNum(long id);

    /**
     * 查询水果信息
     * @param id
     * @return 水果信息
     */
    FruitDto queryFruitById(long id);
}
