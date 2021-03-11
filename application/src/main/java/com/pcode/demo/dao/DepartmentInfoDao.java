package com.pcode.demo.dao;

import com.pcode.demo.dto.CusServiceInfo;
import com.pcode.demo.dto.DepartmentInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DepartmentInfoDao {
    @Insert("insert into department_info (" +
            "depart_id,depart_name,parent_id,depart_level_no,source_no,create_time,create_id,update_time,update_id" +
            ") values" +
            "(#{departmentInfo.departId},#{departmentInfo.departName},#{departmentInfo.parentId}," +
            "#{departmentInfo.departLevelNo},#{departmentInfo.sourceNo},#{departmentInfo.createTime}," +
            "#{departmentInfo.createId},#{departmentInfo.updateTime},#{departmentInfo.updateId})")
    Integer insertDepartment(@Param("departmentInfo") DepartmentInfo departmentInfo);


    @Insert("insert into cus_info(" +
            "cus_id,cus_pwd,cus_name,depart_id,cus_email,sex,nick_name,phone_no,create_cus_id,create_time,position" +
            ") values" +
            " (#{cusServiceInfo.cusId},#{cusServiceInfo.cusPwd},#{cusServiceInfo.cusName},#{cusServiceInfo.departId}" +
            ",#{cusServiceInfo.cusEmail},#{cusServiceInfo.sex},#{cusServiceInfo.nickName},#{cusServiceInfo.phoneNo},#{cusServiceInfo.createCusId},#{cusServiceInfo.createTime},#{cusServiceInfo.position})")
    Integer addNumber(@Param("cusServiceInfo")CusServiceInfo cusServiceInfo);

    @Update("update cus_info set cus_name=#{cusServiceInfo.cusName},nick_name=#{cusServiceInfo.nickName},sex=#{cusServiceInfo.sex},update_cus_id=#{cusServiceInfo.updateCusId}" +
            ",update_time=#{cusServiceInfo.updateTime},phone_no=#{cusServiceInfo.phoneNo},cus_email=#{cusServiceInfo.cusEmail},position=#{cusServiceInfo.position}")
    Integer updateNumberInfo(@Param("cusServiceInfo") CusServiceInfo cusServiceInfo);

    @Select("select depart_id,depart_name,depart_level_no from department_info")
    @Results({
            @Result(property = "departId",column = "depart_id"),
            @Result(property = "departName",column = "depart_name"),
            @Result(property = "departLevelNo",column = "depart_level_no")
    })
    List<DepartmentInfo> departmentList();

    @Select("select depart_id,depart_name,depart_level_no from department_info where depart_id=#{departId}")
    @Results({
            @Result(property = "departId",column = "depart_id"),
            @Result(property = "departName",column = "depart_name"),
            @Result(property = "departLevelNo",column = "depart_level_no")
    })
    DepartmentInfo getDetailById(@Param("departId")String departId);
}
