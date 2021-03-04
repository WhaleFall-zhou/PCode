package com.pcode.demo.util;


import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JsonResponseUtil {
    public static final Logger logger=Logger.getLogger(JsonResponseUtil.class);

    public JsonResponseUtil() {
    }

    public static void write(HttpServletResponse response,Object obj){
        String str= JSON.toJSONString(obj);
        response.setContentType("application/json;charset=utf-8");
        doResponse(response, str);
    }

    public static void printMessage(HttpServletResponse response, String retCode, String retMsg) {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Connection", "Close");
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("retMsg", retMsg);
        resultMap.put("retCode", retCode);
        write(response, (Object)resultMap);
    }

    public static void write(HttpServletResponse response, String jsonStr) {
        doResponse(response, jsonStr);
    }

    private static void doResponse(HttpServletResponse response, String jsonStr) {
        PrintWriter out = null;

        try {
            out = response.getWriter();
            out.print(jsonStr);
            out.flush();
        } catch (Exception var7) {
            logger.error("返回json字符串出错！", var7);
        } finally {
            if (out != null) {
                out.close();
            }

        }

    }
}
