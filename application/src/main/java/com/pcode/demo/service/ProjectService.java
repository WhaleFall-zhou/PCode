package com.pcode.demo.service;

import com.pcode.demo.dto.GeneralDto;
import com.pcode.demo.dto.ItemInfo;
import com.pcode.demo.dto.ProjectVo;

import java.io.IOException;
import java.text.ParseException;

public interface ProjectService {
    GeneralDto addProject(ProjectVo projectVo);
    GeneralDto recentProjectList() throws ParseException;
    GeneralDto projectList() throws ParseException;
    GeneralDto itemListBackLog(String browseId,Integer pageSize,Integer pageNO,String cusId);
    GeneralDto createItem(Integer type);
    GeneralDto demand(String id,String parentId,String types);

    GeneralDto change(String filed,String value,String id ) throws ParseException, ClassNotFoundException, IllegalAccessException, InstantiationException, Exception;

    GeneralDto find(String id);

    GeneralDto addItem(ItemInfo itemInfo) throws ParseException;

    GeneralDto findChildren(String id);

    GeneralDto comment(String id, String value) throws IOException;

    GeneralDto commentList(String id) throws IOException;
}

