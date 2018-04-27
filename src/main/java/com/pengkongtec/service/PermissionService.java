package com.pengkongtec.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 权限Service
 * @ClassName: PermissionService.java 
 * @Description: PermissionService.java
 * @author: xw
 * @date: 2018年4月18日上午9:36:25
 */
public interface PermissionService {
    /**
     * 查询某用户的 角色  菜单列表   权限列表
     *
     * @param username
     * @return
     */
    JSONObject getUserPermission(String username);
}
