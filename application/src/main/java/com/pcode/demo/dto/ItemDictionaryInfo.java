package com.pcode.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDictionaryInfo {
    private Integer id;
    private Integer priority;
    private Integer severity;
    private Integer duplicate_version;
    private Integer recurrence_probability;
    private Integer defect_category;
    private Integer risk;
    private Integer demand_type;
    private Integer demand_source;
    private Integer use_case_type;
    private Integer importance;
    private Integer module;
    private Integer test_library;
    private String name;
}
