package com.pcode.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentInfo {
    private String departId;
    private String departName;
    private String parentId;
    private String departLevelNo;
    private Integer sourceNo;
    private Long createTime;
    private String createId;
    private Long updateTime;
    private String updateId;
    private String departNamePingying;
    private Integer number;
}
