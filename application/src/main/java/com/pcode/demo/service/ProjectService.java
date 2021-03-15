package com.pcode.demo.service;

import com.pcode.demo.dto.GeneralDto;
import com.pcode.demo.dto.ProjectVo;

import java.text.ParseException;

public interface ProjectService {
    GeneralDto addProject(ProjectVo projectVo);
    GeneralDto recentProjectList() throws ParseException;
    GeneralDto enterProject(String projectId) throws ParseException;
    GeneralDto projectList() throws ParseException;
    GeneralDto itemListBackLog(String browseId,Integer pageSize,Integer pageNO);
    GeneralDto createItem(Integer type);
}
