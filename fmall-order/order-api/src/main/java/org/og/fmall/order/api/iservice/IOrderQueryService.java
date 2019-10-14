package org.og.fmall.order.api.iservice;

import com.github.pagehelper.PageInfo;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.order.api.dto.OrderDto;
import org.og.fmall.order.api.dto.OrderResponse;

import java.util.List;

/**
 * @author:ougen
 * @date:2019/9/2813:30
 */
public interface IOrderQueryService {
    /**
     * 根据用户id查询相关订单
     * @param memberId
     * @return
     */
    List<OrderDto> queryAllOrderByMemberId(Long memberId);

    /**
     * 分页查询
     * @param memberId
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<OrderDto> queryPageOrderByMemberId(Long memberId, Integer page, Integer pageSize);
    /**
     * 查询订单
     * @param id
     * @return
     */
    OrderDto queryOrderById(String id);

    /**
     * 查询是否下单成功,并且是否继续查询
     * @param id
     * @return 1为下单成功 0为正在下单 -1为库存不够下单失败
     */
    Integer exeistOrder(Long id,Long fruitId);
}
