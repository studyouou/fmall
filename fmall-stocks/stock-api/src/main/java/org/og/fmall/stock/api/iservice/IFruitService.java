package org.og.fmall.stock.api.iservice;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.stock.api.dto.FruitDto;
import org.og.fmall.stock.api.dto.FruitRequest;
import org.og.fmall.stock.api.dto.FruitResponse;

/**
 * @author:ougen
 * @date:2019/9/2617:26
 */
public interface IFruitService {
    /**
     * 减少水果库存
     * @param id 水果id
     * @param freezeNum 减少数
     * @return 减少水果信息
     */
    @TwoPhaseBusinessAction(name="reduceOrderPrepare",commitMethod = "reduceCommit",rollbackMethod = "reduceRollback")
    FruitResponse reduceOrderPrepare(@BusinessActionContextParameter(paramName = "fruitId") Long id,
                            @BusinessActionContextParameter(paramName = "freezeNum") Integer freezeNum);

    /**
     * tcc分布式事务
     * @param actionContext
     * @return
     */
    boolean reduceCommit(BusinessActionContext actionContext);

    /**
     * tcc分布式事务
     * @param actionContext
     * @return
     */
    boolean reduceRollback(BusinessActionContext actionContext);

    /**
     * 调价水果库存
     * @param id
     * @param num
     * @return 增加水果信息
     */
    FruitResponse incrementNum(long id,int num);

    /**
     * 增加水果种类
     * @param fruitRequest
     * @return 添加水果信息
     */
    FruitResponse addFruit(FruitRequest fruitRequest);

    /**
     * 删除水果种类
     * @param id
     * @return 删除信息
     */
    FruitResponse deleteFruit(long id);
}
