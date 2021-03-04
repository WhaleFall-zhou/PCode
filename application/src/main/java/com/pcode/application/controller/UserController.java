package com.pcode.application.controller;

import com.pcode.application.service.UserService;
import com.pcode.application.dto.CustomerVo;
import com.pcode.application.dto.GeneralDto;
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
            log.error(e.getMessage(),e);
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
            log.error(e.getMessage(),e);
        }
      JsonResponseUtil.write(response,generalDto);

    }
}
