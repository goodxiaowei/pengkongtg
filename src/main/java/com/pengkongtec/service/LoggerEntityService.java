package com.pengkongtec.service;

import com.alibaba.fastjson.JSONObject;

public interface LoggerEntityService {

	public JSONObject findList(JSONObject requestParam);
	
	public void addLoggerEntity(JSONObject requestParam);
}
