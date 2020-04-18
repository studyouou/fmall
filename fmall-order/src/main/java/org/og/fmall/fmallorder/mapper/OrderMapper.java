package org.og.fmall.fmallorder.mapper;

import org.apache.ibatis.annotations.Param;
import org.og.fmall.commonapi.tkmapper.TkMapper;
import org.og.fmall.fmallorder.model.Order;

import java.util.List;

/**
 * @author:ougen
 * @date:2019/9/2420:16
 */
public interface OrderMapper extends TkMapper<Order>{

    List<Order> selectOrderByMemberId(@Param("memberId") long memberId);

}
