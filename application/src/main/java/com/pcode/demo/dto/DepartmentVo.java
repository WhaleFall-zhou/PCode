package com.pcode.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentVo {
    private String departId;//部门id
    private String departName;//部门名称
    private Integer departLeve;//部门级别
    private String parentId;//父级部门id
    private Long changeTime;//改变时间
    private String changeId;//改变id
    private String departNamePinying;//部门名拼音形式
    private String cusName;
    private String cusId;
    private String nickName;
    private Integer sex;
    private String phoneNo;
    private String cusEmail;
    private String createId;
    private Long createTime;
    private String cusPwd;
    private String position;//职位

    private String updateCusId;
    private Long updateTime;

}
