package com.pcode.application.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.pcode.application.dao.ProjectDao;
import com.pcode.application.dao.UserInfoDao;
import com.pcode.application.dto.CusServiceInfo;
import com.pcode.application.dto.GeneralDto;
import com.pcode.application.dto.ProjectInfo;
import com.pcode.application.dto.ProjectVo;
import com.pcode.application.service.ProjectService;
import com.pcode.application.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService {
    final static int MAX_NUMBER=5;
    @Resource
    ProjectDao projectDao;
    @Resource
    UserInfoDao userInfoDao1;
    @Autowired
    private StringRedisTemplate redisTemplate;
    /*
    * 创建一个项目，添加组员关系，并给创建者将项目id加入redis中(最近项目)
    */
    @Override
    public GeneralDto addProject(ProjectVo projectVo) {
        String userId = redisTemplate.opsForValue().get("user");
        ProjectInfo info = new ProjectInfo();
        info.setCreated_at(System.currentTimeMillis());
        info.setCreated_by(userId);
        info.setName(projectVo.getName());
        info.setLogo(projectVo.getLogo());
        String projectId = RandomUtil.getRandomString(20);
        info.setId(projectId);
        projectDao.addProject(info);
        if(StringUtils.isNotBlank(projectVo.getCusIds())){
            String[] cusIds = projectVo.getCusIds().split(",");
            ArrayList<String> cusIdlist = new ArrayList<>();
            Collections.addAll(cusIdlist,cusIds);
            projectDao.addCusIdInProject(cusIdlist,projectId);
        }
        String key="recent_browse_lis:"+userId;
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> range = zSet.reverseRangeWithScores(key, 0, MAX_NUMBER - 1);
        ArrayList<String> list = new ArrayList<>();
        for(ZSetOperations.TypedTuple<String> temp:range){
            list.add(temp.getValue());
        }
        GeneralDto<Object> generalDto = new GeneralDto<>();
        generalDto.setRetMsg("操作成功");
        generalDto.setRetCode("000000");
        return generalDto;
    }

    @Override
    public GeneralDto projectList() throws ParseException {
        GeneralDto<ProjectInfo<CusServiceInfo>> generalDto = new GeneralDto<>();
        String userId = redisTemplate.opsForValue().get("user");
        String key="recent_browse_lis:"+userId;
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> range = zSet.reverseRangeWithScores(key, 0, MAX_NUMBER - 1);
        ArrayList<String> list = new ArrayList<>();
        for(ZSetOperations.TypedTuple<String> temp:range){
            list.add(temp.getValue());
        }
        if(list.size()>0){
            List<ProjectInfo<CusServiceInfo>> projects = projectDao.projectById(list);
            generalDto.setItems(this.turnKey(projects));
        }
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");

        return generalDto;
    }

    /*
    * 去重，限制存放最大个数，过期时间（未实现）
    */
    @Override
    public GeneralDto enterProject(String projectId) throws ParseException {
        GeneralDto<ProjectInfo<CusServiceInfo>> generalDto = new GeneralDto<>();
        String userId = redisTemplate.opsForValue().get("user");
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        String key="recent_browse_lis:"+userId;
        zSet.add(key,projectId,System.currentTimeMillis());
        Long size = zSet.size(key);
        Set<ZSetOperations.TypedTuple<String>> set = zSet.reverseRangeByScoreWithScores(key, 0, MAX_NUMBER - 1);
        if(size>MAX_NUMBER){
            zSet.add(key,set);
        }
        ArrayList<String> list = new ArrayList<>();
        list.add(projectId);
        List<ProjectInfo<CusServiceInfo>> project = projectDao.projectById(list);
        generalDto.setItems( this.turnKey(project));
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }
    public List<ProjectInfo<CusServiceInfo>> turnKey(List<ProjectInfo<CusServiceInfo>> project) throws ParseException {
        ArrayList<ProjectInfo<CusServiceInfo>> projectList= (ArrayList<ProjectInfo<CusServiceInfo>>) project;
        if(!projectList.isEmpty()){
            ArrayList<String> ids = new ArrayList<>();
            for(ProjectInfo temp:projectList){
                ids.add(temp.getCreated_by());
            }
            log.info(JSONObject.toJSONString(ids));
            if(!ids.isEmpty()){
                List<CusServiceInfo> userNameById = userInfoDao1.getUserNameById(ids);
                log.info(JSONObject.toJSONString(userNameById));
                for(ProjectInfo temp:projectList){
                    for (CusServiceInfo var1:userNameById){
                        if(temp.getCreated_by().equals(var1.getCusId())){
                            temp.setMember(var1);
                            temp.setTime(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(temp.getCreated_at())));
                        }
                    }
                }
            }
        }
        log.info(JSONObject.toJSONString(projectList));
        return projectList;
    }
}