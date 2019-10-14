package org.og.fmall.commonapi.tkmapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author:ougen
 * @date:2019/9/2511:15
 */
public interface TkMapper<T> extends Mapper<T>,MySqlMapper<T>{
}
