package com.pengkongtec.utils;

import java.util.UUID;

/**
 * 生成随机码的工具类
 * @ClassName: RandomUtil.java 
 * @Description: RandomUtil.java
 * @author: xw
 * @date: 2018年4月18日下午8:44:31
 */
public class RandomUtil {
    /**
     * 生成前缀+32位编码 用作id
     *
     * @return string
     */
    public static String genarateId(String prefix) {
        String uuid = prefix + UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}
