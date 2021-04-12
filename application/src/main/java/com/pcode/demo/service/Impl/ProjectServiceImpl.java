package com.pcode.demo.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.pcode.demo.dao.ProjectDao;
import com.pcode.demo.dao.UserInfoDao;
import com.pcode.demo.dto.*;
import com.pcode.demo.es.ESUtil;
import com.pcode.demo.es.ElasticsearchConfig;
import com.pcode.demo.service.ProjectService;
import com.pcode.demo.util.ParemeterUstil;
import com.pcode.demo.util.QueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService {
     static int MAX_NUMBER=5;
    @Autowired
    ProjectDao projectDao;
    @Autowired
    UserInfoDao userInfoDao1;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    public KafkaTemplate kafkaTemplate;
    /*
    * 创建一个项目，添加组员关系，并给创建者将项目id加入redis中(最近项目)详情
    */
    @Override
    public GeneralDto addProject(ProjectVo projectVo) {
        String userId = redisTemplate.opsForValue().get("user");
        long time = System.currentTimeMillis();
        ProjectInfo info = new ProjectInfo();
        info.setCreated_at(time);
        info.setCreated_by(userId);
        info.setName(projectVo.getName());
        info.setLogo(projectVo.getLogo());
        info.setDescription(projectVo.getDescription());
        info.setTeam(projectVo.getTeam());
        info.setTemplate_type(projectVo.getType());
        String projectId = UUID.randomUUID().toString();
        info.setId(projectId);
        projectDao.addProject(info);
        String key="recent_browse_lis:"+userId;
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        Long size = zSet.size(key);
        if(size>MAX_NUMBER){
            zSet.removeRange(key,0,(size-MAX_NUMBER)-1);
        }
        zSet.add(key,projectId,time);
        JSONObject message = new JSONObject();
        message.put("projectInfo",info);
        kafkaTemplate.send("add_in_project",JSONObject.toJSONString(message, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));
        GeneralDto<Object> generalDto = new GeneralDto<>();
        generalDto.setRetMsg("操作成功");
        generalDto.setRetCode("000000");
        return generalDto;
    }
    /*
    * 查最近浏览数据
    */
    @Override
    public GeneralDto recentProjectList() throws ParseException {
        GeneralDto<ProjectInfo<CusServiceInfo>> generalDto = new GeneralDto<>();
        String userId = redisTemplate.opsForValue().get("user");
        String key="recent_browse_lis:"+userId;
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> range = zSet.reverseRangeWithScores(key, 0, MAX_NUMBER - 1);
        ArrayList<String> list = new ArrayList<>();
        for(ZSetOperations.TypedTuple<String> temp:range){
            list.add(temp.getValue());
        }
        log.info("recentProjectList: the data in redis is :{}",list);
        if(list.size()>0){
            List<ProjectInfo<CusServiceInfo>> projects = projectDao.projectById(list);
            for(ProjectInfo<CusServiceInfo>temp:projects){
                temp.setNumber(temp.getTeam().split(",").length);
            }
            generalDto.setItems(this.turnKey(projects));
        }
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");

        return generalDto;
    }


    @Override
    public GeneralDto projectList() throws ParseException {
        GeneralDto<ProjectInfo<CusServiceInfo>> generalDto = new GeneralDto<>();
        String userId = redisTemplate.opsForValue().get("user");
        List<ProjectInfo> infoList = projectDao.getIdAneTeam();
        List<String> projectIds = infoList.stream().filter(projectInfo -> projectInfo.getTeam().contains(userId)).map(ProjectInfo::getId).collect(Collectors.toList());
        if(projectIds.size()>0){
            List<ProjectInfo<CusServiceInfo>> projectInfoList = this.turnKey(projectDao.projectById(projectIds));
            for (ProjectInfo<CusServiceInfo>temp:projectInfoList){
                temp.setNumber(temp.getTeam().split(",").length);
            }
            log.info("projectList:{}",projectInfoList);
            generalDto.setItems(projectInfoList);
        }
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        log.info("generalDto:{}",generalDto);
        return generalDto;
    }
    /*
     * 最近浏览数据放入redis中，
     * 要求：数据去重，限制存放最大个数，过期时间（未实现）
     */
    @Override
    public GeneralDto itemListBackLog(String browseId,Integer pageSize,Integer pageNo,String cusId) {
        GeneralDto<ItemInfo<CusServiceInfo>> generalDto = new GeneralDto<>();
        String userId = redisTemplate.opsForValue().get("user");
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        String key="recent_browse_lis:"+userId;
        zSet.add(key,browseId,System.currentTimeMillis());
        Long size = zSet.size(key);
        if(size>MAX_NUMBER){
            zSet.removeRange(key,0,(size-MAX_NUMBER)-1);
        }
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto createItem(Integer type) {
        GeneralDto<ItemDictionaryInfo> generalDto = new GeneralDto<>();
        List<ItemDictionaryInfo> item = projectDao.getItemByType(type);
        generalDto.setItems(item);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto demand(String id,String parentId,String types) {
        GeneralDto<ItemInfo<CusServiceInfo>> generalDto = new GeneralDto<>();
        String[] split=null;
        ArrayList<Integer> integers = new ArrayList<>();
        if(Strings.isNotBlank(types)) {
            split = types.split(",");
            for(String i:split){
                integers.add(Integer.valueOf(i));
            }
        }
        List<ItemInfo<CusServiceInfo>> item = projectDao.getItemList(id,parentId,integers);
        ArrayList<String> list = new ArrayList<>();
        list.add(id);
        List<ProjectInfo<CusServiceInfo>> projectInfo = projectDao.projectById(list);
        item.stream().forEach(itemInfo -> {
            itemInfo.setId(projectInfo.get(0).getLogo().concat("-").concat(itemInfo.getId()));
            if(itemInfo.getStart_time()!=null) {
                itemInfo.setStartTime(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(itemInfo.getStart_time())));
            }
            if(itemInfo.getEnd_time()!=null) {
                itemInfo.setEndTime(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(itemInfo.getEnd_time())));
            }
            if(Strings.isNotBlank(itemInfo.getParticipant())){
                ArrayList list1 = new ArrayList<String>(Arrays.asList(itemInfo.getParticipant().split(",")));
                List<CusServiceInfo> userNameById = userInfoDao1.getUserNameById(list1);
                itemInfo.setParticipantList(userNameById);
                for(CusServiceInfo info:userNameById){
                    if(info.getCusId().equals(itemInfo.getCreate_by())){
                        itemInfo.setPrincipal(new CusServiceInfo(info));
                    }
                }

            }
        });
        List<ProjectInfo<CusServiceInfo>> projectInfoList = projectDao.projectById(list);
        if (!projectInfoList.isEmpty()) {
            generalDto.setItem(projectInfoList.get(0));
        }
        generalDto.setItems(item);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto change(String filed,String value,String id ) throws Exception {
        GeneralDto generalDto = new GeneralDto<>();
        ItemInfo itemInfo = new ItemInfo<>();
        Class<ItemInfo> c = ItemInfo.class;
        Field declaredField = c.getDeclaredField(filed);
        declaredField.setAccessible(true);
        if(filed.equals("statue")){
            declaredField.set(itemInfo,Integer.valueOf(value));
        }else {
            declaredField.set(itemInfo, value);
        }
        itemInfo.setId(id);
        System.out.println(itemInfo.toString());
        if(Strings.isNotBlank(itemInfo.getId())){
            if(Strings.isNotBlank(itemInfo.getStartTime())) {
                itemInfo.setStart_time(new SimpleDateFormat("yyyy年MM月dd日").parse(itemInfo.getStartTime()).getTime());
            }
            if(Strings.isNotBlank(itemInfo.getEndTime())) {
                itemInfo.setEnd_time(new SimpleDateFormat("yyyy年MM月dd日").parse(itemInfo.getEndTime()).getTime());
            }
            String[] split = itemInfo.getId().split("-");
            projectDao.change(itemInfo,Integer.valueOf(split[split.length-1]));
        }
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto find(String id) {
        GeneralDto<CusServiceInfo> generalDto = new GeneralDto<>();
        if(Strings.isNotBlank(id)){
            ProjectInfo projectById = projectDao.getProjectById(id);
            if(Strings.isNotBlank(projectById.getTeam())) {
                ArrayList list = new ArrayList<String>(Arrays.asList(projectById.getTeam().split(",")));
                List<CusServiceInfo> userList = userInfoDao1.getUserNameById(list);
                generalDto.setItems(userList);
            }
        }
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto addItem(ItemInfo itemInfo) throws ParseException {
        GeneralDto<CusServiceInfo> generalDto = new GeneralDto<>();
        if(Strings.isNotBlank(itemInfo.getStartTime())){
            itemInfo.setStart_time(new SimpleDateFormat("yyyy-MM-dd").parse(itemInfo.getStartTime()).getTime());
        }
        if(Strings.isNotBlank(itemInfo.getEndTime())){
            itemInfo.setEnd_time(new SimpleDateFormat("yyyy-MM-dd").parse(itemInfo.getEndTime()).getTime());
        }
        itemInfo.setCreated_at(System.currentTimeMillis());
        itemInfo.setCreate_by(redisTemplate.opsForValue().get("user"));
        itemInfo.setParticipant(itemInfo.getPrincipal_id());
        itemInfo.setStatue(0);
        projectDao.insert(itemInfo);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto findChildren(String id) {
        GeneralDto<ItemInfo> generalDto = new GeneralDto<>();
       if(Strings.isNotBlank(id)){
           ItemInfo itemById = projectDao.getItemById(Integer.valueOf(id.split("-")[1]));
           ArrayList list=new ArrayList<String>(Arrays.asList(itemById.getChildren_id().split(",")));
           List<ItemInfo> itemListById = projectDao.getItemListById(list);
           ArrayList<String> list1 = new ArrayList<>();
           list1.add(itemById.getBrowse_id());
           List<ProjectInfo<CusServiceInfo>> projectInfo = projectDao.projectById(list);
           itemListById.stream().forEach(itemInfo -> {
               itemInfo.setId(projectInfo.get(0).getLogo().concat(itemInfo.getId()));
           });
           generalDto.setItem(itemListById);
       }
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto comment(String id, String value) throws IOException {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        ItemInfo itemById = projectDao.getItemById(Integer.valueOf(id.split("-")[1]));
        ESUtil esUtil = new ESUtil();
        JSONObject var2 = new JSONObject();
        var2.put("operator_id",redisTemplate.opsForValue().get("user"));
        var2.put("operated",id);
        var2.put("organization",itemById.getBrowse_id());
        var2.put("time", new SimpleDateFormat("MM月dd日 HH:mm").format(System.currentTimeMillis()));
        var2.put("operation",value);
        esUtil.addData(var2 ,"operation",null);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto commentList(String id) throws IOException {
        GeneralDto<JSONObject> generalDto = new GeneralDto<>();
        RestHighLevelClient client = ElasticsearchConfig.getRestHighLevelClient();
        SearchRequest searchRequest = new SearchRequest("operation");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryCondition<Object> condition = new QueryCondition<>();
        QueryBuilder queryBuilder = condition.operated(id.split("-")[1]).getResult();
        searchSourceBuilder.query(queryBuilder).sort("time.keyword", SortOrder.ASC);
        log.info("builder:{}",searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit var :hits){
            list.add(var.getSourceAsMap());
        }
        List<JSONObject> list1 = new ArrayList<>();
        for (Map<String, Object> var :list){
            JSONObject jsonObject = new JSONObject();
            CusServiceInfo operator_id = userInfoDao1.getUserInfo((String) var.get("operator_id"));
            jsonObject.put("operation",operator_id);
            jsonObject.put("time",var.get("time"));
            jsonObject.put("comment",var.get("operation"));
            list1.add(jsonObject);
        }
        log.info("commentList:{}",JSONObject.toJSONString(list1));
        generalDto.setItems(list1);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    public List<ProjectInfo<CusServiceInfo>> turnKey(List<ProjectInfo<CusServiceInfo>> project) throws ParseException {
        log.info("turnKey - before- project:{}",project);
        ArrayList<ProjectInfo<CusServiceInfo>> projectList= (ArrayList<ProjectInfo<CusServiceInfo>>) project;
        if(!projectList.isEmpty()){
            HashSet<String> ids = new HashSet<>();
            for(ProjectInfo temp:projectList){
                ids.add(temp.getCreated_by());
            }
            log.info("ids:"+JSONObject.toJSONString(ids));
            if(!ids.isEmpty()){
                List<CusServiceInfo> userNameById = userInfoDao1.getUserNameById(new ArrayList<>(ids));
                log.info(JSONObject.toJSONString(userNameById));
                for(ProjectInfo temp:projectList){
                    for (CusServiceInfo var1:userNameById){
                        if(temp.getCreated_by().equals(var1.getCusId())){
                            CusServiceInfo customerVo = new CusServiceInfo();
                            BeanUtils.copyProperties(var1,customerVo);
                            temp.setMember(customerVo);
                            temp.setTime(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(temp.getCreated_at())));
                            break;
                        }
                    }
                }
            }
        }
        log.info(JSONObject.toJSONString(projectList));
        return projectList;
    }
}
