package com.pcode.demo.dao;

import com.pcode.demo.dto.CusServiceInfo;
import com.pcode.demo.dto.ItemDictionaryInfo;
import com.pcode.demo.dto.ItemInfo;
import com.pcode.demo.dto.ProjectInfo;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

public interface ProjectDao {

    @Insert("insert into browse (" +
            "id,color,name,logo,created_at,created_by,team,template_type,updated_at,updated_by,description" +
            ") values (" +
            "#{projectInfo.id},#{projectInfo.color},#{projectInfo.name},#{projectInfo.logo},#{projectInfo.created_at},#{projectInfo.created_by},#{projectInfo.team},#{projectInfo.template_type},#{projectInfo.updated_at},#{projectInfo.updated_by},#{projectInfo.description})")
    void addProject(@Param("projectInfo") ProjectInfo projectInfo);

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
    @Select("select id,color,name,logo,created_at,created_by,template_type,team from browse where id=#{id}")
    ProjectInfo getProjectById(@Param("id") String id);

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

    @Select("select * from item where browse_id=#{browse_id} AND type in (0,1,2,3,4)")
    ArrayList<ItemInfo<CusServiceInfo>> itemListBackLog(@Param("browse_id")String browseId);

    @Select("select field_id,field_name from item_dictionary where field_type=#{type}")
    @Results({
            @Result(property = "fieldId",column = "field_id"),
            @Result(property = "fieldName",column = "field_name"),
    })
    List<ItemDictionaryInfo> getItemByType(@Param("type") Integer type);

    @Select("<script>" +
            "select * from item where browse_id=#{browse_id} AND type in " +
            "<foreach item='item' collection='type' open='(' close=')' separator=','>" +
            "#{item}" +
            "</foreach>" +
            "<if test='parentId!=null'>" +
            "AND children_id=#{parentId}" +
            "</if>" +
            "</script>")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "principal_id",column = "principal_id"),
            @Result(property = "create_at",column = "create_at"),
            @Result(property = "create_by",column = "create_by"),
            @Result(property = "Participant",column = "Participant"),
            @Result(property = "browse_id",column = "browse_id"),
            @Result(property = "title",column = "title"),
            @Result(property = "statue",column = "statue"),
            @Result(property = "start_time",column = "start_time"),
            @Result(property = "end_time",column = "end_time"),
            @Result(property = "feature",column = "feature"),
            @Result(property = "description",column = "description"),
            @Result(property = "type",column = "type"),
            @Result(property = "children_id",column = "children_id"),
            @Result(property = "create_by",column = "create_by"),
            @Result(property = "principal",javaType = com.pcode.demo.dto.CusServiceInfo.class,many = @Many(select = "getUserInfo"),column = "principal_id"),

    })
    List<ItemInfo<CusServiceInfo>> getItemList(@Param("browse_id") String browse_id,@Param("parentId")String parentId,@Param("type")List<Integer> type);

    @Select("<script>" +
            "select * from item where id in " +
            "<foreach item='item' collection='type' open='(' close=')' separator=','>" +
            "#{item}" +
            "</foreach>" +
            "<if test='parentId!=null'>" +
            "AND children_id=#{parentId}" +
            "</if>" +
            "</script>")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "principal_id",column = "principal_id"),
            @Result(property = "create_at",column = "create_at"),
            @Result(property = "create_by",column = "create_by"),
            @Result(property = "Participant",column = "Participant"),
            @Result(property = "browse_id",column = "browse_id"),
            @Result(property = "title",column = "title"),
            @Result(property = "statue",column = "statue"),
            @Result(property = "start_time",column = "start_time"),
            @Result(property = "end_time",column = "end_time"),
            @Result(property = "feature",column = "feature"),
            @Result(property = "description",column = "description"),
            @Result(property = "type",column = "type"),
            @Result(property = "children_id",column = "children_id"),
            @Result(property = "create_by",column = "create_by"),
            @Result(property = "principal",javaType = com.pcode.demo.dto.CusServiceInfo.class,many = @Many(select = "getUserInfo"),column = "principal_id"),

    })
    List<ItemInfo<CusServiceInfo>> getItemListById(@Param("id")List<Integer> id);

    @Select("select color,nick_name from cus_info where cus_id=#{cusId}")
    List<CusServiceInfo>getUserInfo(@Param("cusId")String cusId);

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "principal_id",column = "principal_id"),
            @Result(property = "create_at",column = "create_at"),
            @Result(property = "create_by",column = "create_by"),
            @Result(property = "Participant",column = "Participant"),
            @Result(property = "browse_id",column = "browse_id"),
            @Result(property = "title",column = "title"),
            @Result(property = "statue",column = "statue"),
            @Result(property = "start_time",column = "start_time"),
            @Result(property = "end_time",column = "end_time"),
            @Result(property = "feature",column = "feature"),
            @Result(property = "description",column = "description"),
            @Result(property = "type",column = "type"),
            @Result(property = "children_id",column = "children_id"),
            @Result(property = "create_by",column = "create_by")
    })
    @Select("select * from item where id=#{id}")
    ItemInfo getItemById(@Param("id")Integer id);

    @Update("<script>" +
            "update item set" +
            "<if test='item.principal_id!=null'>" +
            "principal_id=#{item.principal_id}" +
            "</if>" +
            "<if test='item.Participant!=null'>" +
            "<choose>" +
            "<when test='item.principal_id!=null'>" +
            ",Participant=#{item.Participant}" +
            "</when>" +
            "<otherwise>" +
            "Participant=#{item.Participant}" +
            "</otherwise>" +
            "</choose>" +
            "</if>" +
            "<if test='item.statue!=null'>" +
            "<choose>" +
            "<when test='item.principal_id!=null||item.Participant!=null'>" +
            ",statue=#{item.statue}" +
            "</when>" +
            "<otherwise>" +
            "statue=#{item.statue}" +
            "</otherwise>" +
            "</choose>" +
            "</if>" +
            "<if test='item.title!=null'>" +
            "<choose>" +
            "<when test='item.principal_id!=null||item.Participant!=null||item.statue!=null'>" +
            ",title= #{item.title}" +
            "</when>" +
            "<otherwise>" +
            "title= #{item.title}" +
            "</otherwise>" +
            "</choose>" +
            "</if>" +
            "<if test='item.browse_id!=null'>" +
            "<choose>" +
            "<when test='item.principal_id!=null||item.Participant!=null||item.statue!=null||item.title!=null'>" +
            ",browse_id=#{item.browse_id}" +
            "</when>" +
            "<otherwise>" +
            "browse_id=#{item.browse_id}" +
            "</otherwise>" +
            "</choose>" +
            "</if>" +
            "<if test='item.start_time!=null'>" +
            "<choose>" +
            "<when test='item.principal_id!=null||item.Participant!=null||item.statue!=null||item.title!=null||item.browse_id!=null'>" +
            ",start_time=#{item.start_time}" +
            "</when>" +
            "<otherwise>" +
            "start_time=#{item.start_time}" +
            "</otherwise>" +
            "</choose>" +
            "</if>" +
            "<if test='item.end_time!=null'>" +
            "<choose>" +
            "<when test='item.principal_id!=null||item.Participant!=null||item.statue!=null||item.title!=null||item.browse_id!=null||item.start_time!=null'>" +
            ",end_time=#{item.end_time}" +
            "</when>" +
            "<otherwise>" +
            "end_time=#{item.end_time}" +
            "</otherwise>" +
            "</choose>" +
            "</if>" +
            "<if test='item.description!=null'>" +
            "<choose>" +
            "<when test='item.principal_id!=null||item.Participant!=null||item.statue!=null||item.title!=null||item.browse_id!=null||item.end_time!=null||item.start_time!=null'>" +
            ",description=#{item.description}" +
            "</when>" +
            "<otherwise>" +
            "description=#{item.description}" +
            "</otherwise>" +
            "</choose>" +
            "</if>" +
            "<if test='item.feature!=null'>" +
            "<choose>" +
            "<when test='item.principal_id!=null||item.Participant!=null||item.statue!=null||item.title!=null||item.browse_id!=null||item.end_time!=null||item.start_time!=null||item.description!=null'>" +
            ",feature=#{item.feature}" +
            "</when>" +
            "<otherwise>" +
            "feature=#{item.feature}" +
            "</otherwise>" +
            "</choose>" +
            "</if>" +
            "<if test='item.children_id!=null'>" +
            "<choose>" +
            "<when test='item.principal_id!=null||item.Participant!=null||item.statue!=null||item.title!=null||item.browse_id!=null||item.end_time!=null||item.start_time!=null||item.description!=null||item.feature!=null'>" +
            ",children_id=#{item.children_id}" +
            "</when>" +
            "<otherwise>" +
            "children_id=#{item.children_id}" +
            "</otherwise>" +
            "</choose>" +
            "</if>" +
            "where id=#{id}" +
            "</script>")
    void change(@Param("item")ItemInfo itemInfo,@Param("id")Integer id);

    @Insert("insert into item (statue,create_by,created_at,principal_id,Participant,title,browse_id,start_time,end_time,type,description,feature)" +
            "value" +
            "(#{item.statue},#{item.create_by},#{item.created_at},#{item.principal_id},#{item.Participant},#{item.title},#{item.browse_id},#{item.start_time},#{item.end_time},#{item.type},#{item.description},#{item.feature})")
    void insert(@Param("item")ItemInfo itemInfo);
}
