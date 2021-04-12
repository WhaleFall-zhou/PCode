package com.pcode.demo.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.pcode.demo.dao.ProjectDao;
import com.pcode.demo.dao.UserInfoDao;
import com.pcode.demo.dto.*;
import com.pcode.demo.es.ElasticsearchConfig;
import com.pcode.demo.service.UserService;
import com.pcode.demo.util.QueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectDao projectDao;

    public GeneralDto<Object> login(CustomerVo customerVo) throws Exception {
        CusServiceInfo cusInfo = userInfoDao.getPassWordByName(customerVo.getUserName());
        GeneralDto<Object> generalDto = new GeneralDto<>();
        if(cusInfo.getCusPwd()!=null&&cusInfo.getCusId()!=null) {
            if (cusInfo.getCusPwd().equals(customerVo.getPassWord())) {
                redisTemplate.opsForValue().set("user", cusInfo.getCusId(), 30, TimeUnit.MINUTES);
                generalDto.setRetCode("000000");
                generalDto.setRetMsg("操作成功");
                log.info("success:"+ cusInfo.getCusId());
            }
        }
        return generalDto;
    }

    @Override
    public GeneralDto<Object> getUserInfo() {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        String userId = redisTemplate.opsForValue().get("user");
        CusServiceInfo userInfo = userInfoDao.getUserInfo(userId);
        generalDto.setItem(userInfo);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto userList() {
        GeneralDto<CusServiceInfo> generalDto = new GeneralDto<>();
        List<CusServiceInfo> number = userInfoDao.getNumber();
        if(!number.isEmpty()){
            generalDto.setItems(number);
            generalDto.setRetCode("000000");
            generalDto.setRetMsg("操作成功");
        }
        return generalDto;
    }

    @Override
    public GeneralDto init(CustomerVo customerVo) {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        CusServiceInfo cusServiceInfo = new CusServiceInfo();
        cusServiceInfo.setCusName(customerVo.getUserName());
        cusServiceInfo.setCusEmail(customerVo.getCusEmail());
        cusServiceInfo.setPhoneNo(customerVo.getPhoneNo());
        cusServiceInfo.setCusPwd(customerVo.getPassWord());
        cusServiceInfo.setCreateTime(System.currentTimeMillis());
        cusServiceInfo.setCreateCusId(redisTemplate.opsForValue().get("user"));
        cusServiceInfo.setCusId(UUID.randomUUID().toString());
        cusServiceInfo.setSex(customerVo.getSex());
        cusServiceInfo.setDepartId(customerVo.getDepartId());
        cusServiceInfo.setPosition(cusServiceInfo.getPosition());
        int i = userInfoDao.initUser(cusServiceInfo);
        if (i>0){
            generalDto.setRetCode("000000");
            generalDto.setRetMsg("操作成功");
        }
        return generalDto;
    }

    @Override
    public GeneralDto deleteMember(String cusId) {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        log.info("userServiceImpl -> cusId:{}",cusId);
        int i = userInfoDao.deleteById(cusId);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto remove(String cusIds, String departId) {
        List<String> ids = Arrays.asList(cusIds.split(","));
        GeneralDto<Object> generalDto = new GeneralDto<>();
        int i = userInfoDao.remove(ids,departId);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto advice() throws IOException {
        GeneralDto<Advice<CusServiceInfo, ProjectInfo>> generalDto = new GeneralDto<>();
        String cusId = redisTemplate.opsForValue().get("user");
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        //key_count:用户上次查看通知的条数（用于动态展示通知条数）
        String key_count="advice_project_number:"+cusId;
        Set<ZSetOperations.TypedTuple<String>> set = zSet.reverseRangeWithScores(key_count, 0, 0);
        Integer number = 0;
        for(ZSetOperations.TypedTuple<String> temp:set){
            number= Integer.valueOf((temp.getValue()));
        }
        String key="advice_project:"+cusId;
        Long startTime = null,endTime;
        Set<ZSetOperations.TypedTuple<String>> range = zSet.reverseRangeWithScores(key, 0, 0);
        for(ZSetOperations.TypedTuple<String> temp:range){
            startTime= Long.valueOf(temp.getValue());
        }
        endTime=System.currentTimeMillis();
        RestHighLevelClient client = ElasticsearchConfig.getRestHighLevelClient();
        SearchRequest searchRequest = new SearchRequest("operation");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryCondition<Object> condition = new QueryCondition<>();
        QueryBuilder queryBuilder = condition.parameter("operated", cusId).time(startTime,endTime).getResult();
        searchSourceBuilder.query(queryBuilder).sort("time.keyword", SortOrder.DESC);
        log.info("builder:{}",searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit var :hits){
            list.add(var.getSourceAsMap());
        }
        log.info("advice elasticsearch hits:"+ JSONObject.toJSONString(list));
        HashSet ids = new HashSet<>();
        ArrayList<String> projectIds = new ArrayList<>();
        for (Map<String, Object> var :list){
            ids.add(var.get("operator_id"));
            ids.add(var.get("operated"));
            projectIds.add(String.valueOf(var.get("organization")));
        }
        ArrayList arrayList = new ArrayList<>(ids);
        List<CusServiceInfo> userInfo = null;
        int count=0;
        if(!arrayList.isEmpty()) {
             userInfo = userInfoDao.getUserNameById(arrayList);
            List<ProjectInfo<CusServiceInfo>> projectInfoList = projectDao.projectById(projectIds);
            ArrayList<Advice<CusServiceInfo, ProjectInfo>> list1 = new ArrayList<>();
            for(Map<String,Object> var1:list){
                Advice<CusServiceInfo, ProjectInfo> infoAdvice = new Advice<>();
                userInfo.stream().forEach(t->{
                    if(t.getCusId().equals(var1.get("operator_id"))){
                        infoAdvice.setOperator(t);
                    }
                });
                projectInfoList.stream().forEach(t->{
                    if(t.getId().equals(var1.get("organization"))){
                        infoAdvice.setOrganization(t);
                    }
                });
                infoAdvice.setOperation(String.valueOf(var1.get("operation")).replaceAll("\\{\\}","你"));
                infoAdvice.setTime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(Long.valueOf(String.valueOf(var1.get("time"))))));
                log.info("advice detail:{}",infoAdvice);
                list1.add(infoAdvice);
                count=list1.size()-number;
                generalDto.setItems(list1);
            }
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("count",count);
        generalDto.setItem(map);
        generalDto.setRetMsg("操作成功");
        generalDto.setRetCode("000000");
        return generalDto;
    }

    @Override
    public GeneralDto viewed(Integer count) {
        GeneralDto<Advice<CusServiceInfo, ProjectInfo>> generalDto = new GeneralDto<>();
        String cusId = redisTemplate.opsForValue().get("user");
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        //key_count:用户上次查看通知的条数（用于动态展示通知条数）
        String key_count="advice_project_number:"+cusId;
        Set<ZSetOperations.TypedTuple<String>> set = zSet.reverseRangeWithScores(key_count, 0, 0);
        Integer number = 0;
        for(ZSetOperations.TypedTuple<String> temp:set){
            number= Integer.valueOf((temp.getValue()));
        }
        zSet.add(key_count, String.valueOf(number+count),System.currentTimeMillis());
        if (zSet.size(key_count)>1){
            zSet.removeRange(key_count,0,zSet.size(key_count)-2);
        }
        generalDto.setRetMsg("操作成功");
        generalDto.setRetCode("000000");
        return generalDto;
    }

}
