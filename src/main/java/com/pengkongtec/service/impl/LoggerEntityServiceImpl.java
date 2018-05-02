package com.pengkongtec.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.pengkongtec.bean.LoggerEntity;
import com.pengkongtec.dao.LoggerEntityDao;
import com.pengkongtec.service.LoggerEntityService;
import com.pengkongtec.utils.CommonUtil;

@Service
public class LoggerEntityServiceImpl implements LoggerEntityService {

	@Resource
	private LoggerEntityDao loggerEntityDao;

	@Override
	public JSONObject findList(JSONObject requestParam) {
		CommonUtil.fillPageParam(requestParam);
		Integer count = loggerEntityDao.getCount(requestParam);
		List<JSONObject> list = loggerEntityDao.getList(requestParam);
		return CommonUtil.successPage(requestParam, list, count);
	}

	@Override
	public void addLoggerEntity(JSONObject requestParam) {
		loggerEntityDao.insert(requestParam);
	}


}
