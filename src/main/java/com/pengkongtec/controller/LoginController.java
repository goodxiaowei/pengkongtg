package com.pengkongtec.controller;

import com.alibaba.fastjson.JSONObject;
import com.pengkongtec.service.LoginService;
import com.pengkongtec.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录相关
 * @ClassName: LoginController.java 
 * @Description: LoginController.java
 * @author: xw
 * @date: 2018年4月18日上午9:35:54
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param requestJson
     * @return
     */
    @PostMapping("/auth")
    public JSONObject authLogin(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "username,password");
        logger.debug(loginService.authLogin(requestJson).toJSONString());
        return loginService.authLogin(requestJson);
    }

    /**
     * 查询当前登录用户的信息
     *
     * @return
     */
    @PostMapping("/getInfo")
    public JSONObject getInfo() {
        return loginService.getInfo();
    }

    /**
     * 登出
     *
     * @return
     */
    @PostMapping("/logout")
    public JSONObject logout() {
        return loginService.logout();
    }
}
