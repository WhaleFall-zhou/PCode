package com.pcode.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDictionaryInfo {
    private String fieldId;
    private String  fieldName;
    private Integer fieldType;
}
