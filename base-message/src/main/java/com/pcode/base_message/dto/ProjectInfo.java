package com.pcode.base_message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfo<T> {
    private String id;
    private String color;
    private String name;
    private String logo;
    private Long created_at;
    private String created_by;
    private String team;
    private String template_type;
    private Long updated_at;
    private String updated_by;
    private String time;
    private Integer number; //成员个数
    private String description;
    private T member;

}
