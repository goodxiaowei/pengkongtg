package com.pengkong;

import com.pengkongtec.utils.MD5Util;
import org.junit.Test;

public class TestMd5 {

    @Test
    public void encode(){
        String password = "123456";
        String result = MD5Util.encode(password);
        System.out.println("加密后------------" + result);
        System.out.println("输入值和加密后对比----------" + MD5Util.verifyPassword("12_456",result));
    }
}
