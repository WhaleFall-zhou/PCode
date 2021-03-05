package com.pcode.demo.service.Impl;

import com.pcode.demo.dao.UserInfoDao;
import com.pcode.demo.service.UserService;
import com.pcode.demo.dto.CusServiceInfo;
import com.pcode.demo.dto.CustomerVo;
import com.pcode.demo.dto.GeneralDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public GeneralDto<Object> login(CustomerVo customerVo) throws Exception {
        Pattern pattern = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
        Matcher matcher = pattern.matcher(customerVo.getUserName());
        CusServiceInfo cusInfo =new CusServiceInfo();
        if(matcher.find()==true){
            cusInfo=userInfoDao.getPassWordById(customerVo.getUserName());
        }else {
            cusInfo= userInfoDao.getPassWordByEmail(customerVo.getUserName());
        }
        GeneralDto<Object> generalDto = new GeneralDto<>();
        if(cusInfo.getCusPwd()!=null&&cusInfo.getCusId()!=null) {
            if (cusInfo.getCusPwd().equals(customerVo.getPassWord())) {
                redisTemplate.opsForValue().set("user", cusInfo.getCusId(), 30, TimeUnit.MINUTES);
                generalDto.setRetCode("000000");
                generalDto.setRetMsg("操作成功");
                log.info("success:"+ cusInfo.getCusId());
            }
        }
        return generalDto;
    }

    @Override
    public GeneralDto<Object> getUserInfo() {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        String userId = redisTemplate.opsForValue().get("user");
        CusServiceInfo userInfo = userInfoDao.getUserImfo(userId);
        generalDto.setItem(userInfo);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }
}
