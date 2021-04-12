package com.pcode.demo.es.dao;

import com.pcode.demo.es.ElasticsearchConfig;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class ElasticsearchDao<T> {
    private RestHighLevelClient mClient;
    private static final long TIME_OUT = 3000L;
    private static final long SCROLL_TIME_OUT = 1L;
    private static final int RETRY_TIME = 4;
    private static final int MAX_SIZE = 100;
    private static final int PRECISION_THRESHOLD = 5000;
    private static final String ELASTIC_SEARCH__SUB_AGGREGATION_NAME__TAIL = "_Agg";
    private static final String ELASTIC_SEARCH__SUB_SORT_AGGREGATION_NAME__TAIL = "_SortAgg";
    private static final String ELASTIC_SEARCH__AGGREGATION_NAME = "group_by_state";
    private long mTotal = 0L;
    private static final int SCROLL_SIZE = 50;
    private static Integer AGG_LIMIT_SIZE;

    public ElasticsearchDao() {
        mClient= ElasticsearchConfig.getRestHighLevelClient();

    }

    public void close() {
        try {
            this.mClient.close();
        } catch (IOException e) {
           log.error("elasticsearch close error :{}",e);
        }
        this.mClient = null;
    }

}
