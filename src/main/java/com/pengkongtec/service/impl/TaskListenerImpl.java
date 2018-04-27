package com.pengkongtec.service.impl;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 交易信息审批监听器
 * @ClassName: TaskListenerImpl.java 
 * @Description: TaskListenerImpl.java
 * @author: xw
 * @date: 2018年4月18日下午8:36:37
 */
@Service
public class TaskListenerImpl implements TaskListener {
	Logger logger = LoggerFactory.getLogger(TaskListenerImpl.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -18408538527033872L;

	@Override
	public void notify(DelegateTask task) {
		task.addCandidateUser("tradeProcesser");
		logger.debug("监听组ID产生成功");
	}
}
