package com.pcode.application.controller;

import com.alibaba.fastjson.JSONObject;
import com.pcode.application.dto.GeneralDto;
import com.pcode.application.dto.ProjectVo;
import com.pcode.application.service.ProjectService;
import com.pcode.application.util.JsonResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    public ProjectService projectService;

    @RequestMapping(value = "/addProject",method = RequestMethod.POST)
    public void addProject(HttpServletRequest request, HttpServletResponse response, ProjectVo projectVo){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        log.info("addProject:"+ JSONObject.toJSONString(projectVo));
        try{
            generalDto=projectService.addProject(projectVo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }

    /**
     * 获取最近浏览项目,从redis中获取最近浏览项目id，再从数据库中查项目具体信息
     */
    @RequestMapping(value = "/projectList",method = RequestMethod.POST)
    public void projectList(HttpServletRequest request,HttpServletResponse response){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto=projectService.projectList();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");

        }
        JsonResponseUtil.write(response,generalDto);
    }

    /*
    * 点击进入项目，将该项目id存入redis中的recent_project(hash表)中
    *
    */
    @RequestMapping(value = "/enterProject",method = RequestMethod.GET)
    public void enterProject(HttpServletRequest request,HttpServletResponse response,String projectId){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
           generalDto= projectService.enterProject(projectId);
        }catch (Exception e){
            log.info(e.getMessage(),e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作成功");
        }
        JsonResponseUtil.write(response,generalDto);
    }
}
