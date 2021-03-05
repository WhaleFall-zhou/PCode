package com.pcode.demo.dao;

import com.pcode.demo.dto.CusServiceInfo;
import com.pcode.demo.dto.ProjectInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProjectDao {

    @Insert("insert into browse (" +
            "id,color,name,logo,created_at,created_by,tream,template_type,updated_at,updated_by" +
            ") values (" +
            "#{projectInfo.id},#{projectInfo.color},#{projectInfo.name},#{projectInfo.logo},#{projectInfo.created_at},#{projectInfo.created_by},#{projectInfo.tream},#{projectInfo.template_type},#{projectInfo.updated_at},#{projectInfo.updated_by})")
    void addProject(@Param("projectInfo") ProjectInfo projectInfo);

    @Select("select id from browse where name=#{name}")
    String getProjectIdByName(@Param("name") String name);

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "color",column = "color"),
            @Result(property = "name",column = "name"),
            @Result(property = "logo",column = "logo"),
            @Result(property = "created_at",column = "created_at"),
            @Result(property = "created_by",column = "created_by"),
            @Result(property = "template_type",column = "template_type"),
            @Result(property = "team",column = "team")
    })
    @Select("<script>select id,color,name,logo,created_at,created_by,template_type,team from browse" +
            " where id in" +
            "<foreach index='index' item='item' collection='projectIdList' open='(' close=')' separator=','>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    List<ProjectInfo<CusServiceInfo>> projectById(@Param("projectIdList")List<String> projectId);

    @Select("select id,team from browse")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "team",column = "team")
    })
    List<ProjectInfo> getIdAneTeam();
}
