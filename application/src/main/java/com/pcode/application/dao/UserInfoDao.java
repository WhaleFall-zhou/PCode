package com.pcode.application.dao;

import com.pcode.application.dto.CusServiceInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserInfoDao {
    @Select("select cus_id,cus_pwd from cus_info where phone_no=#{phoneNo}")
    @Results({
            @Result(property = "cusId",column = "cus_id"),
            @Result(property = "cusPwd",column = "cus_pwd")
    })
    CusServiceInfo getPassWordById(@Param("phoneNo") String phoneNo);

    @Select("select cus_id,cus_pwd from cus_info where cus_email=#{userEmail}")
    @Results({
            @Result(property = "cusId",column = "cus_id"),
            @Result(property = "cusPwd",column = "cus_pwd")
//            @Result(property = "cusName",column = "cus_name"),
//            @Result(property = "phoneNo",column = "phone_no"),
//            @Result(property = "cusEmail",column = "cus_email"),
//            @Result(property = "color",column = "color"),
//            @Result(property = "ticketAuthFlag",column = "ticket_auth_flag"),
//            @Result(property = "replyAuthFlag",column = "reply_auth_flag"),
//            @Result(property = "userAuthFlag",column = "user_auth_flag"),
//            @Result(property = "ticketDocAuthFlag",column = "ticket_doc_auth_flag"),
//            @Result(property = "auditTickerDocFlag",column = "audit_ticket_doc_flag"),
//            @Result(property = "pwdType",column = "pwd_type"),
//            @Result(property = "position",column = "position")
    })
    CusServiceInfo getPassWordByEmail(@Param("userEmail") String userEmail);

    @Select("select cus_id,cus_name,color,phone_no,cus_email,ticket_auth_flag,reply_auth_flag,user_auth_flag,ticket_doc_auth_flag,audit_ticket_doc_flag" +
            ",pwd_type,position from cus_info where cus_id=#{cusId}")
    @Results({
            @Result(property = "cusId",column = "cus_id"),
            @Result(property = "cusName",column = "cus_name"),
            @Result(property = "phoneNo",column = "phone_no"),
            @Result(property = "cusEmail",column = "cus_email"),
            @Result(property = "color",column = "color"),
            @Result(property = "ticketAuthFlag",column = "ticket_auth_flag"),
            @Result(property = "replyAuthFlag",column = "reply_auth_flag"),
            @Result(property = "userAuthFlag",column = "user_auth_flag"),
            @Result(property = "ticketDocAuthFlag",column = "ticket_doc_auth_flag"),
            @Result(property = "auditTickerDocFlag",column = "audit_ticket_doc_flag"),
            @Result(property = "pwdType",column = "pwd_type"),
            @Result(property = "position",column = "position")
    })
    CusServiceInfo getUserImfo(@Param("cusId")String cusId);

    @Update("update cus_info set cus_pwd=#{cusInfo.cusPwd},cus_name=#{cusInfo.cusName},phone_no=#{cusInfo.phoneNo},cus_email=#{cusInfo.cusEmail}" +
            ",color=#{cusInfo.color},ticket_auth_flag=#{cusInfo.ticketAuthFlag},user_auth_flag=#{cusInfo.userAuthFlag},ticket_doc_auth_flag=#{cusInfo.ticketDocAuthFlag}" +
            ",audit_ticket_doc_flag=#{cusInfo.auditTickerDocFlag} where cus_id=#{cusInfo.cusId}")
    void updateCusInfo(@Param("cusInfo") CusServiceInfo cusServiceInfo);

    @Select("<script>" +
            "select cus_id,cus_name,color from cus_info where cus_id in" +
            "<foreach index='index' item='item' collection='cusId' open='(' close=')' separator=','>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    @Results({
            @Result(property = "cusId",column = "cus_id"),
            @Result(property = "cusName",column = "cus_name"),
            @Result(property = "color",column = "color")
    })
    List<CusServiceInfo> getUserNameById(@Param("cusId")List<String> cusId);
}
