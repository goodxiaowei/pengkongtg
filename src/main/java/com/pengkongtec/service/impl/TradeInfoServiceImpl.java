package com.pengkongtec.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.pengkongtec.dao.TradeInfoDao;
import com.pengkongtec.service.BpmService;
import com.pengkongtec.service.TradeInfoService;
import com.pengkongtec.utils.CommonUtil;
import com.pengkongtec.utils.UserUtil;
import com.pengkongtec.utils.constants.Constants;

/**
 * 交易信息ServiceImpl
 * @ClassName: TradeInfoServiceImpl.java 
 * @Description: TradeInfoServiceImpl.java
 * @author: xw
 * @date: 2018年4月18日下午8:38:54
 */
@Service
public class TradeInfoServiceImpl implements TradeInfoService {
	@Resource
	private BpmService bpmService;
	@Resource
	private TradeInfoDao tradeInfoDao;
	@Resource
	private RuntimeService runtimeService;
	
	@Override
	public JSONObject addTradeInfo(@RequestBody JSONObject requestParam) {
		String id = UUID.randomUUID().toString();
		requestParam.put("id", id);
		requestParam.put("status", Constants.TASK_DRAFT);
			//新增一条记录至数据库中
			Integer result = tradeInfoDao.insert(requestParam);
			//启动流程引擎
			bpmService.startProcess(String.valueOf(UserUtil.getUser().getId()));
			if(result > 0){
				return CommonUtil.successJson();
			}else{
				return CommonUtil.failJson();
			}
	}

	@Override
	public JSONObject findList(JSONObject requestParam, String status) {
		String userId = String.valueOf(requestParam.get("userId"));
		CommonUtil.fillPageParam(requestParam);
		List<JSONObject> tradeInfoList = new ArrayList<>();
		List<Task> list = bpmService.findTaskByUserId(userId);
		for (Task task : list) {
			ProcessInstance result = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			String processInstanceId = result.getProcessInstanceId();
			requestParam.put("status", status);
			requestParam.put("processInstanceId", processInstanceId);
			JSONObject tradeInfo = tradeInfoDao.getListById(requestParam);
			tradeInfoList.add(tradeInfo);
		}
		int count = tradeInfoDao.getCount(requestParam);
        return CommonUtil.successPage(requestParam, tradeInfoList, count);
	}

	@Override
	public void completeTaskByUser(String taskId, String userId, String audit) {
		bpmService.completeTaskByUser(taskId, userId, audit);
	}
	
	@Override
	public JSONObject withdraw(JSONObject requestParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTradeInfo(JSONObject requestParam) {
		tradeInfoDao.delete(requestParam);
	}

	@Override
	public JSONObject updateTradeInfo(JSONObject requestParam) {
		Integer result = tradeInfoDao.update(requestParam);
		if(result > 0){
			return CommonUtil.successJson();
		}else{
			return CommonUtil.failJson();
		}
	}

	@Override
	public JSONObject submitApproval(JSONObject requestParam) {
		//修改交易信息状态为审批中
		bpmService.commitApproval(String.valueOf(requestParam.get("taskId")));
		requestParam.put("status", Constants.TASK_APPROVALING);
		Integer result = tradeInfoDao.update(requestParam);
		if(result > 0){
			return CommonUtil.successJson();
		}else{
			return CommonUtil.failJson();
		}
	}

	@Override
	public JSONObject passAppvoval(JSONObject requestParam) {
		if(bpmService.queryProcessIsEnd(String.valueOf(requestParam.get("processInstanceId")))){
			//修改交易信息状态为审批通过
			bpmService.completeTaskByUser(String.valueOf(requestParam.get("taskId")), String.valueOf(requestParam.get("userId")), String.valueOf(requestParam.get("audit")));
			bpmService.deleteProcessInstance(String.valueOf(requestParam.get("processInstanceId")), "");
			requestParam.put("status", Constants.TASK_APPROVALING);
			Integer result = tradeInfoDao.update(requestParam);
			if(result > 0){
				return CommonUtil.successJson();
			}else{
				return CommonUtil.failJson();
			}
		}else{
			return CommonUtil.failJson("该审批流程已结束！");
		}
	}

	@Override
	public JSONObject reject(JSONObject requestParam) {
		// TODO Auto-generated method stub
		return null;
	}

}
