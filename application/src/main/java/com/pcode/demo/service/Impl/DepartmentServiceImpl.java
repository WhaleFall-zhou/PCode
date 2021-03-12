package com.pcode.demo.service.Impl;

import com.pcode.demo.dao.DepartmentInfoDao;
import com.pcode.demo.dto.*;
import com.pcode.demo.service.DepartmentService;
import com.pcode.demo.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentInfoDao departmentInfoDao;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public GeneralDto<Object> departmentCreate(DepartmentVo departmentVo) {
        GeneralDto<Object> generalDto = new GeneralDto<>();
        DepartmentInfo departmentInfo = new DepartmentInfo();
        departmentInfo.setDepartId(redisTemplate.opsForValue().get("user"));
        departmentInfo.setCreateTime(System.currentTimeMillis());
        departmentInfo.setDepartName(departmentVo.getDepartName());
        departmentInfo.setParentId(departmentVo.getParentId());
        departmentInfo.setDepartId(RandomUtil.getRandomString(14));
        DepartmentInfo parent = departmentInfoDao.getDetailById(departmentVo.getParentId());
        departmentInfo.setDepartLevelNo(Strings.isEmpty(parent.getDepartLevelNo())?parent.getDepartId():parent.getDepartLevelNo().concat("|"+parent.getDepartId()));
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
        Pattern pattern = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
        Matcher matcher = pattern.matcher(departmentVo.getConnection());
        if (matcher.find()){
            cusServiceInfo.setPhoneNo(departmentVo.getConnection());
        }else {
            cusServiceInfo.setCusEmail(departmentVo.getConnection());
        }
        cusServiceInfo.setCusId(RandomUtil.getRandomString(12));
        if(!departmentVo.getDepartId().isEmpty()) {
            cusServiceInfo.setDepartId(departmentVo.getDepartId());
        }
        if(!departmentVo.getSex().isEmpty()) {
            cusServiceInfo.setSex(departmentVo.getSex());
        }
        if(!departmentVo.getPosition().isEmpty()){
            cusServiceInfo.setPosition(departmentVo.getPosition());
        }
        cusServiceInfo.setCusPwd(departmentVo.getCusPwd());
        cusServiceInfo.setCreateCusId(redisTemplate.opsForValue().get("user"));
        cusServiceInfo.setCreateTime(System.currentTimeMillis());
        cusServiceInfo.setNickName(departmentVo.getNickName());
        cusServiceInfo.setColor(Color.randomColor(Color.values()).value);
        log.info("addNumber -> cusServiceInfo:{}",cusServiceInfo);
        Integer result = departmentInfoDao.addNumber(cusServiceInfo);
        if(result>0){
            generalDto.setRetCode("000000");
            generalDto.setRetMsg("操作成功");
        }
        return generalDto;
    }

    @Override
    public GeneralDto<Object> updateNumberInfo(DepartmentVo departmentVo) {
        log.info("departmentVo:{}",departmentVo);
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

    @Override
    public GeneralDto departmentList() {
        GeneralDto<DepartmentInfo> generalDto = new GeneralDto<>();
        List<DepartmentInfo> departmentList = departmentInfoDao.departmentList();
        List<CusServiceInfo> member = departmentInfoDao.getNemberInDepartment();
        for(DepartmentInfo var1:departmentList){
            for (CusServiceInfo var2 : member) {
                if (var1.getDepartId().equals(var2.getDepartId())) {
                    var1.setNumber(var2.getNumber());
                }
            }
            if(var1.getNumber()==null){
                var1.setNumber(0);
            }
        }
        log.info("departmentIndo:{}",departmentList);
        generalDto.setItems(departmentList.stream().sorted(Comparator.comparing(DepartmentInfo::getNumber).reversed()).collect(Collectors.toList()));
        generalDto.setRetMsg("操作成功");
        generalDto.setRetCode("000000");
        return generalDto;
    }

    @Override
    public GeneralDto memberInDepartment(String departId) {
        GeneralDto<CusServiceInfo> generalDto = new GeneralDto<>();
        List<CusServiceInfo> member = departmentInfoDao.getAllMemberInDepartment(departId);
        if (!member.isEmpty()){
            generalDto.setItems(member);
        }
        generalDto.setRetCode("000000");
        generalDto.setRetMsg("操作成功");
        return generalDto;
    }

}
