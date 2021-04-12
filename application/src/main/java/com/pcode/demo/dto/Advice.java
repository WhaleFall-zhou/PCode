package com.pcode.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advice<T,C> {
    private T operator;
    private  C organization;
    private String time;
    private String operation;
    private String other;
}
