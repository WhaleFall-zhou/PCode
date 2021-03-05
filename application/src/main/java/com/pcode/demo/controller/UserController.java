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
}
