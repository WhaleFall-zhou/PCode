package com.pcode.demo.Interceptor;


import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String user = redisTemplate.opsForValue().get("user");
            log.info("user:{}",user);
            if(Strings.isNotEmpty(user)){
                redisTemplate.opsForValue().set("user", user, 30, TimeUnit.MINUTES);
                return true;
            }
            response.sendRedirect(request.getContextPath()+"/login");
        }catch (Exception e){
            log.error("Interceptor:{}",e);
        }
        return false;
    }
}
