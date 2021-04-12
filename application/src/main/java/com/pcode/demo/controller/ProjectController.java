package com.pcode.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.pcode.demo.dto.GeneralDto;
import com.pcode.demo.dto.ItemInfo;
import com.pcode.demo.dto.ProjectVo;
import com.pcode.demo.service.ProjectService;
import com.pcode.demo.util.JsonResponseUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@Api("项目控制类")
public class ProjectController {

    @Autowired
    public ProjectService projectService;
    @ApiOperation("新增项目")
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
     * 1、获取最近浏览项目,从redis中获取最近浏览项目id，再从数据库中查项目具体信息
     */
    @RequestMapping(value = "/recentProjectList",method = RequestMethod.POST)
    public void recentProjectList(HttpServletRequest request,HttpServletResponse response){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto=projectService.recentProjectList();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");

        }
        JsonResponseUtil.write(response,generalDto);
    }
    /*
    *查当前用户所在的所有项目
    */
    @RequestMapping(value = "/projectList",method = RequestMethod.POST)
    public void projectList(HttpServletRequest request,HttpServletResponse response){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto= projectService.projectList();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }
    @RequestMapping(value = "/itemList",method = RequestMethod.POST)
    public void itemListBackLog(HttpServletRequest request, HttpServletResponse response, String browseId, Integer pageSize, Integer pageNo,String cusId, Model model) throws ServletException, IOException {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto= projectService.itemListBackLog(browseId,pageSize,pageNo,cusId);
        }catch (Exception e){
            log.error("itemList:{}",e);
            generalDto.setRetMsg("操作失败");
            generalDto.setRetCode("999999");
        }
        response.sendRedirect("/detail/".concat(browseId));

    }

//    /*
//    * 创建工作项，查工作项内的详情
//    */
//    @RequestMapping(value = "/createItem",method = RequestMethod.POST)
//    public void createItem(HttpServletRequest request,HttpServletResponse response,Integer type){
//        GeneralDto<Object> generalDto = new GeneralDto<>();
//        try {
//            generalDto= projectService.createItem(type);
//        }catch (Exception e){
//            log.error("enterProject:{}",e);
//            generalDto.setRetCode("999999");
//            generalDto.setRetMsg("操作失败");
//        }
//        JsonResponseUtil.write(response,generalDto);
//    }
    /*
    *获取需求列表
    */
    @RequestMapping(value = "/demandList",method = RequestMethod.POST)
    public void demand(HttpServletRequest request, HttpServletResponse response, String id, String parent_id,String type){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto= projectService.demand(id,parent_id,type);
        }catch (Exception e){
            log.error("demand:{}",e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
      JsonResponseUtil.write(response,generalDto);
    }
    /*
     *新增工作项
     */
    @RequestMapping(value = "/addItem",method = RequestMethod.POST)
    public void addItem(HttpServletRequest request, HttpServletResponse response, ItemInfo itemInfo){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto=projectService.addItem(itemInfo);
        }catch (Exception e){
            log.error("addItem:{}",e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }
    /*
     *修改
     * id 工作项id
     * key 要修改属性名
     * value 修改的值
     */
    @RequestMapping(value = "/change",method = RequestMethod.POST)
    public void change(HttpServletRequest request, HttpServletResponse response,String filed,String value,String id ){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto = projectService.change(filed,value,id);
        }catch (Exception e){
            log.error("change:{}",e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }
    /*
     *查项目中的成员
     */
    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public void find(HttpServletRequest request, HttpServletResponse response,String  id){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto = projectService.find(id);
        }catch (Exception e){
            log.error("find:{}",e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }
    /*
     *查项目中的子成员
     */
    @RequestMapping(value = "/findChildren",method = RequestMethod.POST)
    public void findChildren(HttpServletRequest request, HttpServletResponse response,String  id){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto = projectService.findChildren(id);
        }catch (Exception e){
            log.error("find:{}",e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }
    /*
     *评论
     */
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public void comment(HttpServletRequest request, HttpServletResponse response,String  id,String value){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto = projectService.comment(id,value);
        }catch (Exception e){
            log.error("find:{}",e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }

    @RequestMapping(value = "/commentList",method = RequestMethod.POST)
    public void commentList(HttpServletRequest request, HttpServletResponse response,String  id){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto = projectService.commentList(id);
        }catch (Exception e){
            log.error("find:{}",e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }
}
