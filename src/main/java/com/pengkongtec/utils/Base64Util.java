package com.pengkongtec.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.IOException;

/**
 * base64加密解密工具类
 */
public class Base64Util {

    public static String encode(String param){
        byte[] bt = param.getBytes();
        @SuppressWarnings("restriction")
		String newKey=(new BASE64Encoder()).encodeBuffer(bt);
        return newKey;
    }

    public static String decode(String param) {
        String result = "";
        try {
            @SuppressWarnings("restriction")
			byte[] bt = (new BASE64Decoder()).decodeBuffer(param);
            result = new String(bt, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
