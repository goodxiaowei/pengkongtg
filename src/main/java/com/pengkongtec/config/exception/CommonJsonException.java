package com.pengkongtec.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.pengkongtec.utils.CommonUtil;
import com.pengkongtec.utils.constants.ErrorEnum;

/**
 * 本系统使用的自定义错误类
 * 比如在校验参数时,如果不符合要求,可以抛出此错误类
 * 拦截器可以统一拦截此错误,将其中json返回给前端
 * @ClassName: CommonJsonException.java 
 * @Description: CommonJsonException.java
 * @author: xw
 * @date: 2018年4月18日下午8:30:05
 */
public class CommonJsonException extends RuntimeException {
    private JSONObject resultJson;

    /**
     * 调用时可以在任何代码处直接throws这个Exception,
     * 都会统一被拦截,并封装好json返回给前台
     *
     * @param errorEnum 以错误的ErrorEnum做参数
     */
    public CommonJsonException(ErrorEnum errorEnum) {
        this.resultJson = CommonUtil.errorJson(errorEnum);
    }

    public CommonJsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public JSONObject getResultJson() {
        return resultJson;
    }

}