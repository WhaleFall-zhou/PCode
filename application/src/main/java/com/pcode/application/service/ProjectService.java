package com.pcode.application.service;

import com.pcode.application.dto.GeneralDto;
import com.pcode.application.dto.ProjectVo;

import java.text.ParseException;

public interface ProjectService {
    GeneralDto addProject(ProjectVo projectVo);
    GeneralDto projectList() throws ParseException;
    GeneralDto enterProject(String projectId) throws ParseException;
}
