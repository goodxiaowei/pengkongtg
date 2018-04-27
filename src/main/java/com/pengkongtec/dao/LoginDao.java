package com.pengkongtec.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/**
 * 登录相关dao
 * @ClassName: LoginDao.java 
 * @Description: LoginDao.java
 * @author: xw
 * @date: 2018年4月18日上午9:32:09
 */
public interface LoginDao {
    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    JSONObject getUser(@Param("username") String username, @Param("password") String password);
}
