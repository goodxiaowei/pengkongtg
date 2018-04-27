package com.pengkongtec.service;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

/**
 * 交易信息Serivice
 * @ClassName: TradeService.java 
 * @Description: TradeService.java
 * @author: xw
 * @date: 2018年4月18日上午9:36:54
 */
public interface TradeInfoService {
	/**
	 * 新增一条交易信息记录
	 * @param entity
	 */
	@Transactional(rollbackFor=Exception.class)
	JSONObject addTradeInfo(JSONObject requestParam);
	
	/**
	 * 删除一条交易信息记录
	 * @Title：
	 * @Description: 
	 * @param tradeInfo
	 * @return void
	 */
	@Transactional(rollbackFor=Exception.class)
	void deleteTradeInfo(JSONObject requestParam);
	
	/**
	 * 修改一条交易信息记录
	 * @Title：
	 * @Description: 
	 * @param tradeInfo
	 * @return void
	 */
	@Transactional(rollbackFor=Exception.class)
	JSONObject updateTradeInfo(JSONObject requestParam);
	
	/**
	 * 查询流程(待办，已办，待审批，已审批)
	 * @param userId
	 * @return
	 */
	JSONObject findList(JSONObject requestParam, String status);
	
	/**
	 * 完成任务
	 * @param taskId
	 * @param userId
	 * @param audit
	 */
	@Transactional(rollbackFor=Exception.class)
	void completeTaskByUser(String taskId,String userId,String audit);
	
	/**
	 * 撤回审批
	 * @Title：
	 * @Description: 
	 * @param requestParam
	 * @return
	 * @return JSONObject
	 */
	@Transactional(rollbackFor=Exception.class)
	JSONObject withdraw(JSONObject requestParam);
	
	/**
	 * 提交审批
	 * @Title：
	 * @Description: 
	 * @param requestParam
	 * @return
	 * @return JSONObject
	 */
	@Transactional(rollbackFor=Exception.class)
	JSONObject submitApproval(JSONObject requestParam);
	
	/**
	 * 审批通过
	 * @Title：
	 * @Description: 
	 * @param requestParam
	 * @return
	 * @return JSONObject
	 */
	@Transactional(rollbackFor=Exception.class)
	JSONObject passAppvoval(JSONObject requestParam);
	
	/**
	 * 驳回审批
	 * @Title：
	 * @Description: 
	 * @param requestParam
	 * @return
	 * @return JSONObject
	 */
	@Transactional(rollbackFor=Exception.class)
	JSONObject reject(JSONObject requestParam);
	
}
