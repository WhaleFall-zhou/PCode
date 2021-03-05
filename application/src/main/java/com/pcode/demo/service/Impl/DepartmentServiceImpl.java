package com.pcode.demo.service.Impl;

import com.pcode.demo.dao.DepartmentInfoDao;
import com.pcode.demo.dto.CusServiceInfo;
import com.pcode.demo.dto.DepartmentInfo;
import com.pcode.demo.dto.DepartmentVo;
import com.pcode.demo.dto.GeneralDto;
import com.pcode.demo.service.DepartmentService;
import com.pcode.demo.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentInfoDao departmentInfoDao;
    @Override
    public GeneralDto<Object> departmentCreate(DepartmentVo departmentVo) {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        DepartmentInfo departmentInfo = new DepartmentInfo();
        departmentVo.setDepartId(RandomUtil.getRandomString(14));
        Integer result = departmentInfoDao.insertDepartment(departmentInfo);
        if(result>0){
            generalDto.setRetMsg("操作成功");
            generalDto.setRetCode("000000");
        }
        return generalDto;
    }

    @Override
    public GeneralDto<Object> addNumber(DepartmentVo departmentVo) {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        CusServiceInfo cusServiceInfo = new CusServiceInfo();
        cusServiceInfo.setCusName(departmentVo.getCusName());
        if(departmentVo.getCusEmail().length()>0){
            cusServiceInfo.setCusEmail(departmentVo.getCusEmail());
        }
        if(departmentVo.getPhoneNo().length()>0){
            cusServiceInfo.setPhoneNo(departmentVo.getPhoneNo());
        }
        cusServiceInfo.setCusId(RandomUtil.getRandomString(12));
        cusServiceInfo.setDepartId(departmentVo.getDepartId());
        cusServiceInfo.setSex(departmentVo.getSex());
        cusServiceInfo.setCusPwd(departmentVo.getCusPwd());
        cusServiceInfo.setCreateCusId(departmentVo.getCusId());
        cusServiceInfo.setCreateTime(departmentVo.getCreateTime());
        Integer result = departmentInfoDao.addNumber(cusServiceInfo);
        if(result>0){
            generalDto.setRetCode("000000");
            generalDto.setRetMsg("操作成功");
        }
        return generalDto;
    }

    @Override
    public GeneralDto<Object> updateNumberInfo(DepartmentVo departmentVo) {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        CusServiceInfo cusServiceInfo = new CusServiceInfo();
        if(departmentVo.getCusName().length()>0)
        cusServiceInfo.setCusName(departmentVo.getCusName());
        if (departmentVo.getNickName().length()>0)
        cusServiceInfo.setNickName(departmentVo.getNickName());
        if(departmentVo.getSex()!=null)
        cusServiceInfo.setSex(departmentVo.getSex());
        if (departmentVo.getPhoneNo().length()>0)
        cusServiceInfo.setPhoneNo(departmentVo.getPhoneNo());
        if(departmentVo.getCusEmail().length()>0)
        cusServiceInfo.setCusEmail(departmentVo.getCusEmail());
        if(departmentVo.getUpdateCusId().length()>0)
        cusServiceInfo.setUpdateCusId(departmentVo.getUpdateCusId());
        if(departmentVo.getUpdateTime()!=null)
        cusServiceInfo.setUpdateTime(departmentVo.getUpdateTime());
        if(departmentVo.getPosition().length()>0)
        cusServiceInfo.setPosition(departmentVo.getPosition());
        Integer result = departmentInfoDao.updateNumberInfo(cusServiceInfo);
        if(result>0){
            generalDto.setRetCode("000000");
            generalDto.setRetMsg("操作成功");
        }
        return generalDto;
    }

}
