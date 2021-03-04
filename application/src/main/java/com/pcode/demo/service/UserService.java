package com.pcode.demo.service;

import com.pcode.demo.dto.CusServiceInfo;
import com.pcode.demo.dto.CustomerVo;
import com.pcode.demo.dto.GeneralDto;

public interface UserService {
    GeneralDto<Object> login(CustomerVo customerVo)throws Exception;
    GeneralDto<Object> getUserInfo();
}
