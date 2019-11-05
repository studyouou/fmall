package org.og.fmall.elasticsearch.api.iservices;

import org.og.fmall.elasticsearch.api.dto.ElasticSearchDto;

import java.util.List;

/**
 * @author: lyp
 * @description:
 * @date: 2019/11/4
 */
public interface IElasticSearchService {
    /**
     *
     * @param index 索引
     * @param type 类型
     * @param id id
     * @param data json数据
     */
    void insert(String index,String type,String id,String data);

    /**
     *
     * @param index 索引
     * @param type 类型
     * @param id 数据
     * @param data 更新数据
     * @return
     */
    void updata(String index,String type,String id,String data);

    /**
     *
     * @param data 查询数据
     * @param size 长度
     * @param from 起点
     * @return
     */
    ElasticSearchDto search(String data,int size,int from);

    /**
     *
     * @param beanList 需要插入的数据bean
     * @param <T>
     */
    <T> void batchInsert(List<T> beanList);
}
