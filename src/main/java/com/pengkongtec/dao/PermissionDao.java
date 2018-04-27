package com.pengkongtec.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.Set;

/**
 * 权限资源Dao
 * @ClassName: PermissionDao.java 
 * @Description: PermissionDao.java
 * @author: xw
 * @date: 2018年4月18日上午9:34:52
 */
public interface PermissionDao {
    /**
     * 查询用户的角色 菜单 权限
     *
     * @param username
     * @return
     */
    JSONObject getUserPermission(String username);

    /**
     * 查询所有的菜单
     *
     * @return
     */
    Set<String> getAllMenu();

    /**
     * 查询所有的权限
     *
     * @return
     */
    Set<String> getAllPermission();
}
