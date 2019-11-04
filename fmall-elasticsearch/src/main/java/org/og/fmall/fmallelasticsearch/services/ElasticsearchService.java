package org.og.fmall.fmallelasticsearch.services;

import com.alibaba.dubbo.config.annotation.Service;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.elasticsearch.api.dto.ElasticSearchDto;
import org.og.fmall.elasticsearch.api.iservices.IElasticSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: og
 * @description:
 * @date: 2019/11/4
 */
@Service
public class ElasticsearchService implements IElasticSearchService {

    private static Logger logger = LoggerFactory.getLogger(ElasticsearchService.class);

    @Autowired
    private RestHighLevelClient client;

    @Override
    public void insert(String index, String type, String id, String data) {
        IndexRequest indexRequest = new IndexRequest().index(index).type(type).id(id).source(data, XContentType.JSON);
        indexRequest.opType(DocWriteRequest.OpType.INDEX);
        client.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                if (indexResponse.getResult() == DocWriteResponse.Result.CREATED){
                    logger.info("不存在改文档，重新创建");
                }else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED){
                    logger.info("修改成功");
                }
            }

            @Override
            public void onFailure(Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public void updata(String index, String type, String id, String data) {
        IndexRequest indexRequest = new IndexRequest().type(type).index(index).id(id).source(data, XContentType.JSON);
        indexRequest.opType(DocWriteRequest.OpType.UPDATE);
        client.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                if (indexResponse.getResult() == DocWriteResponse.Result.CREATED){
                    logger.info("不存在改文档，重新创建");
                }else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED){
                    logger.info("修改成功");
                }
            }

            @Override
            public void onFailure(Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public ElasticSearchDto search(String data) {
        SearchRequest request = new SearchRequest("fruits");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder ();
        sourceBuilder.query(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("fruitName", data).boost(2f))
                .should(QueryBuilders.matchQuery("disciption", data).boost(1f)));
        sourceBuilder.size(10);
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.ASC));
        sourceBuilder.timeout(new TimeValue(10, TimeUnit.SECONDS));
        request.source(sourceBuilder);
        try {
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);
            RestStatus status = response.status();
            if (status != RestStatus.OK){
                logger.error("查询错误");
            }
            boolean timedOut = response.isTimedOut();
            if (timedOut){
                ElasticSearchDto dto = new ElasticSearchDto();
                dto.setCode(CommonEnum.ELASTICSEARCH_TIME_OUT.getCode());
                dto.setMsg(CommonEnum.ELASTICSEARCH_TIME_OUT.getMsg());
                return dto;
            }
            ElasticSearchDto dto = new ElasticSearchDto();
            SearchHits hits = response.getHits();
            dto.setTotal(hits.getTotalHits().value);
            List<String> sources = new ArrayList<>();
            for (SearchHit hit : hits.getHits()){
                sources.add(hit.getSourceAsString());
            }
            dto.setData(sources);
            return dto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
