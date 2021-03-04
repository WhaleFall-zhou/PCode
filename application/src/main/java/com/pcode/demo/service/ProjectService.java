package com.pcode.demo.service;

import com.pcode.demo.dto.GeneralDto;
import com.pcode.demo.dto.ProjectVo;

import java.text.ParseException;

public interface ProjectService {
    GeneralDto addProject(ProjectVo projectVo);
    GeneralDto projectList() throws ParseException;
    GeneralDto enterProject(String projectId) throws ParseException;
}
