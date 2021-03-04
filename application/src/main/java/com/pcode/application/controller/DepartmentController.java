package com.pcode.application.controller;

import com.alibaba.fastjson.JSONObject;
import com.pcode.application.dto.DepartmentVo;
import com.pcode.application.dto.GeneralDto;
import com.pcode.application.service.DepartmentService;
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
    @ResponseBody
    public GeneralDto departmentCreate(HttpServletRequest request, HttpServletResponse response, DepartmentVo departmentVo){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        log.info("departmentInsert:"+ JSONObject.toJSONString(departmentVo));
        try{
            generalDto=departmentService.departmentCreate(departmentVo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        return generalDto;
    }
    /**
    * 添加成员
     * @param cusName
     * @param nickName
     * @param sex
     * @param phoneNo
     * @param cusEmail
     * @param createId
     * @param createTime
     * @param cusPwd
     * @param position 
     * */
    @RequestMapping(value = "/addNumber",method = RequestMethod.POST)
    @ResponseBody
    public GeneralDto addNumber(HttpServletRequest request,HttpServletResponse response,DepartmentVo departmentVo){
        log.info("addNumber:"+JSONObject.toJSONString(departmentVo));
        GeneralDto<Object> generalDto=null;
        try{
            generalDto= departmentService.addNumber(departmentVo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            generalDto.setRetMsg("999999");;
            generalDto.setRetMsg("操作失败");
        }
        return generalDto;

    }
    /**
     * 操作成员信息
     * @param cusName
     * @param nickName
     * @param phoneNo
     * @param cusEmail
     * @param updateCusId
     * @param updateTime
     * @param position
     * @param sex
     * */
    @RequestMapping(value = "/updateNumberInfo",method = RequestMethod.POST)
    public GeneralDto updateNumberInfo(HttpServletRequest request,HttpServletResponse response,DepartmentVo departmentVo){
        log.info("updateNUmberInfo:"+JSONObject.toJSONString(departmentVo));
        GeneralDto<Object> generalDto=null;
        try{
            generalDto=departmentService.updateNumberInfo(departmentVo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
        }
        return generalDto;
    }
}
