package org.og.fmall.fmallstock.mapper;

import org.apache.ibatis.annotations.Param;
import org.og.fmall.commonapi.tkmapper.TkMapper;
import org.og.fmall.fmallstock.model.Fruit;

/**
 * @author:ougen
 * @date:2019/9/2815:17
 */
public interface FruitMapper extends TkMapper<Fruit> {
    Integer reduceNumById(@Param("id") long id,@Param("num") int num);

    void incrementNumById(@Param("id") long id,@Param("num") int num);

    Integer queryStock(@Param("id") long id);
}
