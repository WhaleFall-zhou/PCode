package com.pcode.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.pcode.demo.dto.DepartmentVo;
import com.pcode.demo.dto.GeneralDto;
import com.pcode.demo.service.DepartmentService;
import com.pcode.demo.util.JsonResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/departmentCreate",method = RequestMethod.POST)
    public void departmentCreate(HttpServletRequest request, HttpServletResponse response, DepartmentVo departmentVo){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        log.info("departmentInsert:"+ JSONObject.toJSONString(departmentVo));
        try{
            generalDto=departmentService.departmentCreate(departmentVo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }
    /**
    * 添加成员
     * */
    @RequestMapping(value = "/addNumber",method = RequestMethod.POST)
    public void addNumber(HttpServletRequest request,HttpServletResponse response,DepartmentVo departmentVo){
        log.info("addNumber:"+JSONObject.toJSONString(departmentVo));
        GeneralDto<Object> generalDto=null;
        try{
            generalDto= departmentService.addNumber(departmentVo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            generalDto.setRetMsg("999999");;
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);

    }
    /**
     * 操作成员信息
     * */
    @RequestMapping(value = "/updateNumberInfo",method = RequestMethod.POST)

    public void updateNumberInfo(HttpServletRequest request,HttpServletResponse response,DepartmentVo departmentVo){
        log.info("updateNUmberInfo:"+JSONObject.toJSONString(departmentVo));
        GeneralDto<Object> generalDto=null;
        try{
            generalDto=departmentService.updateNumberInfo(departmentVo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }

    @RequestMapping(value = "/departmentList",method = RequestMethod.POST)
    public void departmentList(HttpServletRequest request,HttpServletResponse response){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto=departmentService.departmentList();
        }catch (Exception e){
            log.error("departmentList:{}",e);
            generalDto.setRetMsg("操作失败");
            generalDto.setRetCode("999999");
        }
        JsonResponseUtil.write(response,generalDto);
    }
    /*
    * 部门的成员
    */
    @RequestMapping(value = "/memberInDepartment",method = RequestMethod.POST)
    public void memberInDepartment(HttpServletRequest request,HttpServletResponse response,String departId){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto=departmentService.memberInDepartment(departId);
        }catch (Exception e){
            log.error("memberInDepartment:{}",e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }
}
