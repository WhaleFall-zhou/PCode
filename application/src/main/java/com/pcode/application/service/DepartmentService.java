package com.pcode.application.service;

import com.pcode.application.dto.DepartmentVo;
import com.pcode.application.dto.GeneralDto;

public interface DepartmentService {
    GeneralDto<Object> departmentCreate(DepartmentVo departmentVo);

    GeneralDto<Object> addNumber(DepartmentVo departmentVo);

    GeneralDto<Object> updateNumberInfo(DepartmentVo departmentVo);
}
