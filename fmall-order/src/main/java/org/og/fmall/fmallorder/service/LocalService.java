package org.og.fmall.fmallorder.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.og.fmall.fmallorder.model.Order;

/**
 * @description:
 * @author: OuGen
 * @create: 2020-04-16 09:30
 **/
@LocalTCC
public interface LocalService {

    @TwoPhaseBusinessAction(name = "orderCreate" , commitMethod = "orderCreateCommit", rollbackMethod = "orderCreateRollback")
    public int createPrepareLocalOrder(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "order") Order order);

    public boolean orderCreateCommit(BusinessActionContext actionContext);

    public boolean orderCreateRollback(BusinessActionContext actionContext);
}
