package com.pcode.application.util;


public class RandomUtil {

    public static String getRandomString(int len){
        StringBuffer builder = new StringBuffer();
        for(int j=0;j<len;j++){
            String  v = String.valueOf((int) (Math.random() * 3+1));
            switch (v){
                case "1":{
                    double num = Math.random() * (57 - 48) + 48;
                    char c=(char)num;
                    builder.append(c);
                    break;
                }
                case "2":{
                    double lower = Math.random() * (90 - 65) + 65;
                    char c=(char)lower;
                    builder.append(c);
                    break;
                }
                case "3":{
                    double up = Math.random() * (122 - 97) + 97;
                    char c=(char)up;
                    builder.append(c);
                    break;
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getRandomString(12));
    }
}
