package com.pcode.demo.dto;

import lombok.Data;

public enum Color {

    DEEP_BLUE("DEEP_BLUE","rgb(134, 138, 246)"),
    GREEN("GREEN","rgb(132, 225, 126)"),
    RED("RED","rgb(250, 136, 136)"),
    LIGHT_BLUE("LIGHT_BLUE","rgb(93, 207, 255)"),
    PURPLE("PURPLE","rgb(154, 126, 244)"),
    ORANGE("ORANGE","rgb(255, 159, 115)"),
    PINK("PINK","rgb(251, 127, 183)");

    public String key;
    public String value;
    Color(String key, String value) {
        this.key=key;
        this.value=value;
    }

    public static Color randomColor(Color[] colors){
        return colors[(int)(Math.random()* colors.length)];
    }

}
