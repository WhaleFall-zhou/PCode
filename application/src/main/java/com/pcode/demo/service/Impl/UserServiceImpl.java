package com.pcode.demo.service.Impl;

import com.pcode.demo.dao.UserInfoDao;
import com.pcode.demo.service.UserService;
import com.pcode.demo.dto.CusServiceInfo;
import com.pcode.demo.dto.CustomerVo;
import com.pcode.demo.dto.GeneralDto;
import com.pcode.demo.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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
        CusServiceInfo cusInfo = userInfoDao.getPassWordByName(customerVo.getUserName());
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
        CusServiceInfo userInfo = userInfoDao.getUserInfo(userId);
        generalDto.setItem(userInfo);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto userList() {
        GeneralDto<CusServiceInfo> generalDto = new GeneralDto<>();
        List<CusServiceInfo> number = userInfoDao.getNumber();
        if(!number.isEmpty()){
            generalDto.setItems(number);
            generalDto.setRetCode("000000");
            generalDto.setRetMsg("操作成功");
        }
        return generalDto;
    }

    @Override
    public GeneralDto init(CustomerVo customerVo) {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        CusServiceInfo cusServiceInfo = new CusServiceInfo();
        cusServiceInfo.setCusName(customerVo.getUserName());
        cusServiceInfo.setCusEmail(customerVo.getCusEmail());
        cusServiceInfo.setPhoneNo(customerVo.getPhoneNo());
        cusServiceInfo.setCusPwd(customerVo.getPassWord());
        cusServiceInfo.setCreateTime(System.currentTimeMillis());
        cusServiceInfo.setCreateCusId(redisTemplate.opsForValue().get("user"));
        cusServiceInfo.setCusId(RandomUtil.getRandomString(12));
        cusServiceInfo.setSex(customerVo.getSex());
        cusServiceInfo.setDepartId(customerVo.getDepartId());
        cusServiceInfo.setPosition(cusServiceInfo.getPosition());
        int i = userInfoDao.initUser(cusServiceInfo);
        if (i>0){
            generalDto.setRetCode("000000");
            generalDto.setRetMsg("操作成功");
        }
        return generalDto;
    }

    @Override
    public GeneralDto deleteMember(String cusId) {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        int i = userInfoDao.deleteById(cusId);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

    @Override
    public GeneralDto remove(String cusIds, String departId) {
        List<String> ids = Arrays.asList(cusIds.split(","));
        GeneralDto<Object> generalDto = new GeneralDto<>();
        int i = userInfoDao.remove(ids,departId);
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }
}
