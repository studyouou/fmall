package org.og.fmall.elasticsearch.api.dto;

import lombok.Data;
import org.og.fmall.commonapi.dto.ResponseContext;

import java.util.List;

/**
 * @author: lyp
 * @description:
 * @date: 2019/11/4
 */
@Data
public class ElasticSearchDto extends ResponseContext {

    private Long total;

    private List<String> data;
}
