package com.pcode.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ParemeterUstil {
    public static <E>List<E> getPaging(int pageNo,int pageSize,List<E> list){
         return list.stream().skip((pageNo - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }
}
