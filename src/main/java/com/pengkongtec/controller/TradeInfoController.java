package com.pengkongtec.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pengkongtec.service.TradeInfoService;
import com.pengkongtec.utils.constants.Constants;

/**
 * 交易信息controller
 * @ClassName: TradeInfoController.java 
 * @Description: TradeInfoController.java
 * @author: xw
 * @date: 2018年4月18日下午8:34:03
 */
@RestController
@RequestMapping("/tradeInfo")
public class TradeInfoController {

	@Resource
	private TradeInfoService tradeInfoService;
	
	/**
	 * 新增交易记录 status:草稿
	 * @Title：
	 * @Description: 
	 * @param tradeInfo
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "addTradeInfo", method = RequestMethod.PUT)
	public String addTradeInfo(@RequestBody JSONObject requestParam){
		return JSON.toJSONString(tradeInfoService.addTradeInfo(requestParam));
	}
	
	/**
	 * 查询待办任务
	 * @Title：
	 * @Description: 
	 * @param requestParam
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "findListDraft", method = RequestMethod.GET)
	public String findListDraft(@RequestBody JSONObject requestParam){
		return JSON.toJSONString(tradeInfoService.findList(requestParam, Constants.TASK_DRAFT));
	}
	
	/**
	 * 修改交易信息
	 * @Title：
	 * @Description: 
	 * @param requestParam
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@RequestBody JSONObject requestParam){
		return JSON.toJSONString(tradeInfoService.updateTradeInfo(requestParam));
	}
	
	/**
	 * 提交交易信息到审批
	 * @Title：
	 * @Description: 
	 * @param requestParam
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "submit", method = RequestMethod.POST)
	public String submitApproval(@RequestBody JSONObject requestParam){
		return JSON.toJSONString(tradeInfoService.submitApproval(requestParam));
	}
	
	/**
	 * 查询已办任务
	 * @Title：
	 * @Description: 
	 * @param requestParam
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "findListAppovaling", method = RequestMethod.GET)
	public String findListAppovaling(@RequestBody JSONObject requestParam){
		return JSON.toJSONString(tradeInfoService.findList(requestParam, Constants.TASK_APPROVALED));
	}
	
	/**
	 * 查询待审批任务
	 * @Title：
	 * @Description: 
	 * @param requestParam
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "findListAppovalPending", method = RequestMethod.GET)
	public String findListAppovalPending(@RequestBody JSONObject requestParam){
		return JSON.toJSONString(tradeInfoService.findList(requestParam, Constants.TASK_APPROVALING));
	}
	
	/**
	 * 审核通过交易信息
	 * @Title：
	 * @Description: 
	 * @param requestParam
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "passApproval", method = RequestMethod.POST)
	public String approval(@RequestBody JSONObject requestParam){
		return JSON.toJSONString(tradeInfoService.passAppvoval(requestParam));
	}
	
	/**
	 * 查询审批通过任务
	 * @Title：
	 * @Description: 
	 * @param requestParam
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "findListAppovaled", method = RequestMethod.POST)
	public String findListAppovaled(@RequestBody JSONObject requestParam){
		return JSON.toJSONString(tradeInfoService.findList(requestParam, Constants.TASK_APPROVALED));
	}
}
