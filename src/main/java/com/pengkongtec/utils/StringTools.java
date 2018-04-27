package com.pengkongtec.utils;

/**
 * 判断String工具类
 * @ClassName: StringTools.java 
 * @Description: StringTools.java
 * @author: xw
 * @date: 2018年4月18日下午8:44:41
 */
public class StringTools {

    public static boolean isNullOrEmpty(String str) {
        return null == str || "".equals(str) || "null".equals(str);
    }

    public static boolean isNullOrEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }
}
