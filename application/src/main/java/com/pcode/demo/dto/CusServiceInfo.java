package com.pcode.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CusServiceInfo {
    private String cusId;//成员Id
    private String cusPwd;//登录密码
    private String cusName;//登录用户名称
    private String departId;//部门id
    private Integer loginStatus;//0未登录，1登录
    private String phoneNo;//手机号码
    private String cusEmail;//邮箱地址
    private String createCusId;//创建人
    private Long createTime;//创建时间
    private String updateCusId;//更新人
    private Long updateTime;//更新时间
    private String sex;//性别 0：女  1：男
    private String nickName;//成员名称
    private String loginIp;
    private Long loginTime;//登录时间
    private String color;//头像图片背景颜色
    private String position;//职位
    private Integer ticketAuthFlag;//查看工作项权限标识，0查看所有的工作项（默认），1查看自己组内的工作项，2查看分配给自己的工作项
    private Integer replyAuthFlag;//回复权限标识，0公开和私密回复，1仅私密回复（默认）
    private Integer userAuthFlag;//查看普通用户权限标识，0允许管理普通用户（默认），1只能查看普通用户
    private Integer ticketDocAuthFlag;//查看知识库权限，0查看所有的文档（默认），1增删改查所有的文档（操作之后都需要审核）
    private Integer auditTickerDocFlag;//正对管理员，是否可审核知识库，0可审核（默认），1不可审核（可审核时只针对管理员）
    private Integer pwdType;//密码类型 0 重置密码  1 非重置密码
    private Integer number;


    public CusServiceInfo(CusServiceInfo cusServiceInfo) {
        this.cusId=cusServiceInfo.getCusId();
        this.cusPwd=cusServiceInfo.getCusPwd();
        this.cusName=cusServiceInfo.getCusName();
        this.departId=cusServiceInfo.getDepartId();
        this.loginStatus=cusServiceInfo.getLoginStatus();
        this.phoneNo=cusServiceInfo.getPhoneNo();
        this.cusEmail=cusServiceInfo.getCusEmail();
        this.createCusId=cusServiceInfo.getCreateCusId();
        this.createTime=cusServiceInfo.getCreateTime();
        this.updateCusId=cusServiceInfo.getUpdateCusId();
        this.updateTime=cusServiceInfo.getUpdateTime();
        this.sex=cusServiceInfo.getSex();

        this.nickName=cusServiceInfo.getNickName();
        this.loginIp=cusServiceInfo.getLoginIp();
        this.loginTime=cusServiceInfo.getLoginTime();
        this.color=cusServiceInfo.getColor();
        this.position=cusServiceInfo.getPosition();
        this.ticketAuthFlag=cusServiceInfo.getTicketAuthFlag();
        this.replyAuthFlag=cusServiceInfo.getReplyAuthFlag();
        this.userAuthFlag=cusServiceInfo.getUserAuthFlag();
        this.ticketDocAuthFlag=cusServiceInfo.getTicketDocAuthFlag();
        this.auditTickerDocFlag=cusServiceInfo.getAuditTickerDocFlag();
        this.pwdType=cusServiceInfo.getPwdType();
        this.number=cusServiceInfo.getNumber();


    }
}
