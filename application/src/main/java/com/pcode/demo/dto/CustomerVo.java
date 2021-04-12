package com.pcode.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVo {
    private String userName;
    private String cusId;//成员id
    private String passWord;//登录密码
    private Integer loginFlag;
    private String phoneNo;
    private String cusEmail;
    private String randomKey;
    private String randomCode;
    private String departId;
    private String sex;
    private String position;
}
