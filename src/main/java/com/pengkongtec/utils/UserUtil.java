package com.pengkongtec.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import com.alibaba.fastjson.JSONObject;
import com.pengkongtec.bean.User;
import com.pengkongtec.utils.constants.Constants;

/**
 * 用户信息工具类
 * @ClassName: UserUtil.java 
 * @Description: UserUtil.java
 * @author: xw
 * @date: 2018年4月18日下午8:45:20
 */
public class UserUtil {

	/**
	 * 获取当前用户信息
	 * @Title：
	 * @Description: 
	 * @return
	 * @return User
	 */
	public static User getUser(){
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        User user = new User();
        user.setId(Integer.parseInt(String.valueOf(userInfo.get("userId"))));
        user.setUsername(String.valueOf(userInfo.get("username")));
        return user;
	}
}
