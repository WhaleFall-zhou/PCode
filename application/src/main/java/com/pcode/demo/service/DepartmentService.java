package com.pcode.demo.service;

import com.pcode.demo.dto.DepartmentVo;
import com.pcode.demo.dto.GeneralDto;

public interface DepartmentService {
    GeneralDto<Object> departmentCreate(DepartmentVo departmentVo);

    GeneralDto<Object> addNumber(DepartmentVo departmentVo);

    GeneralDto<Object> updateNumberInfo(DepartmentVo departmentVo);

    GeneralDto departmentList();
}
