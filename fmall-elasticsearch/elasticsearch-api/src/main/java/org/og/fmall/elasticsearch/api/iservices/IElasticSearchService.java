package org.og.fmall.elasticsearch.api.iservices;

import org.og.fmall.elasticsearch.api.dto.ElasticSearchDto;

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
     * @param  sort 排序
     * @return
     */
    ElasticSearchDto search(String data);
}
