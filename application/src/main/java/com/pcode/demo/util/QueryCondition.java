package com.pcode.demo.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

@Slf4j
public class QueryCondition<T> {
    private T conditon;
    private StringBuilder query=new StringBuilder();
    private BoolQueryBuilder shoule=QueryBuilders.boolQuery();

    public QueryCondition() {
    }

    public QueryCondition(T conditon) {
        this.conditon = conditon;
    }
    public QueryCondition time(Long startTime,Long endTime){
        query.append(" AND time:["+startTime+" TO "+endTime+"]");
        return this;
    }
    public QueryBuilder getResult(){
        log.info(query.toString());
        log.info(JSONObject.toJSONString(QueryBuilders.queryStringQuery(query.toString())));
        return QueryBuilders.queryStringQuery(query.toString());
    }
    public QueryCondition parameter(String name,String value){
        query.append(name+":"+value);
        return this;
    }
    public QueryCondition operated(String operated){
        query.append("operated:"+operated);
        return this;
    }
}
