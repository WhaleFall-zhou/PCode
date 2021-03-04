package com.pcode.application.service;

import com.pcode.application.dto.CustomerVo;
import com.pcode.application.dto.GeneralDto;

public interface UserService {
    GeneralDto<Object> login(CustomerVo customerVo)throws Exception;
    GeneralDto<Object> getUserInfo();
}
