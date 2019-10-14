package org.og.fmall.fmallshopping.mapper;

import org.apache.ibatis.annotations.Param;
import org.og.fmall.commonapi.tkmapper.TkMapper;
import org.og.fmall.fmallshopping.model.Panel;

import java.util.List;

/**
 * @author:ougen
 * @date:2019/10/422:23
 */
public interface HomeMapper extends TkMapper<Panel>{
    List<Panel> queryPanels(@Param("location") Integer location,@Param("offset") Integer offset);

    List<Panel> queryDefaultPanel(@Param("location") Integer location,@Param("offset") Integer offset);
}
