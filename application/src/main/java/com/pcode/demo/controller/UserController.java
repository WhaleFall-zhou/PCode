package com.pcode.demo.controller;

import com.pcode.demo.service.UserService;
import com.pcode.demo.dto.CustomerVo;
import com.pcode.demo.dto.GeneralDto;
import com.pcode.demo.util.JsonResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/serviceLogin",method = RequestMethod.POST)
    public void serviceLogin(HttpServletRequest request, HttpServletResponse response, CustomerVo customerVo){
        GeneralDto<Object> generalDto=new GeneralDto<>();
        try {
            generalDto= userService.login(customerVo);
        } catch (Exception e) {
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("登录失败");
            log.error("serviceLogin:{}",e);
        }
        JsonResponseUtil.write(response,generalDto);
    }

    @RequestMapping(value = "/getUserInfo",method =RequestMethod.POST)
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto=userService.getUserInfo();
        }catch (Exception e){
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
            log.error("getUserInfo:{}",e);
        }
      JsonResponseUtil.write(response,generalDto);

    }

    @RequestMapping(value = "/userList",method = RequestMethod.POST)
    public void userList(HttpServletRequest request,HttpServletResponse response){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try{
            generalDto=userService.userList();
        }catch (Exception e){
            generalDto.setRetCode("999999");
            generalDto.setRetMsg("操作失败");
            log.error("userList:{}",e);
        }
        JsonResponseUtil.write(response,generalDto);
    }
    @RequestMapping(value = "/init",method = RequestMethod.POST)
    public void initUser(HttpServletRequest request,HttpServletResponse response,CustomerVo customerVo){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto= userService.init(customerVo);
        }catch (Exception e){
            log.error("initUser:{}",e);
            generalDto.setRetCode("9999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }
    //删除成员
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String deleteMember(HttpServletRequest request,HttpServletResponse response,String cusId){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto= userService.deleteMember(cusId);
        }catch (Exception e){
            log.error("deleteMember:{}",e);
            generalDto.setRetCode("9999999");
            generalDto.setRetMsg("操作失败");
        }
        return "/admin";
    }
    //移动成员
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public void remove(HttpServletRequest request,HttpServletResponse response,String cusIds,String departId){
        GeneralDto<Object> generalDto = new GeneralDto<>();
        try {
            generalDto= userService.remove(cusIds, departId);
        }catch (Exception e){
            log.error("initUser:{}",e);
            generalDto.setRetCode("9999999");
            generalDto.setRetMsg("操作失败");
        }
        JsonResponseUtil.write(response,generalDto);
    }
}
