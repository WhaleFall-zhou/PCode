package com.pcode.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVo {
    private String name;
    private String logo;
    private String description;
    private String type;
    private String team;

}
