package com.pengkongtec.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 登录Service
 * @ClassName: LoginService.java 
 * @Description: LoginService.java
 * @author: xw
 * @date: 2018年4月18日下午8:41:11
 */
public interface LoginService {
    /**
     * 登录表单提交
     *
     * @param jsonObject
     * @return
     */
    JSONObject authLogin(JSONObject jsonObject);

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    JSONObject getUser(String username, String password);

    /**
     * 查询当前登录用户的权限等信息
     *
     * @return
     */
    JSONObject getInfo();

    /**
     * 退出登录
     *
     * @return
     */
    JSONObject logout();
}
