package com.xxl.job.executor.service.jobhandler;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.pengkongtec.service.LoginService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;


/**
 * 自定义job
 * @ClassName: MyJobHandler.java 
 * @Description: MyJobHandler.java
 * @author: xw
 * @date: 2018年4月23日下午6:19:33
 */

@JobHandler(value="MyJobHandler")
@Component
public class MyJobHandler extends IJobHandler {

	Logger logger = LoggerFactory.getLogger(MyJobHandler.class);
	@Resource
	private LoginService loginService;
	
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		JSONObject user = loginService.getUser("admin", "123456");
		logger.debug("<-----------------打印日志----------------->");
		System.out.println("---------------->" + user.toJSONString());
		return SUCCESS;
	}

}
