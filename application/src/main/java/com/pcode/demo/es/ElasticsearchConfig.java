package com.pcode.demo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticsearchConfig {
    private static RestHighLevelClient restHighLevelClient;

    public synchronized static RestHighLevelClient getRestHighLevelClient(){
        if(restHighLevelClient==null){
            restHighLevelClient=new RestHighLevelClient(RestClient.builder( new HttpHost("192.168.43.220", 9200, "http")));
        }
        return restHighLevelClient;
    }
}
