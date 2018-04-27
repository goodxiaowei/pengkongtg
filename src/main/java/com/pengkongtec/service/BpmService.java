package com.pengkongtec.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengkongtec.dao.TradeInfoDao;

/**
 * 审批相关Service
 * @ClassName: BpmService.java 
 * @Description: BpmService.java
 * @author: xw
 * @date: 2018年4月18日下午8:40:49
 */
@Service
public class BpmService {
	
	public final Logger logger = LoggerFactory.getLogger(BpmService.class);

	@Autowired
	private TradeInfoDao tradeInfoDao;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private static RepositoryService repositoryService;
	
	/**
	 * 部署流程定义
	 * @Title：
	 * @Description: 
	 * @param BpmService.java
	 * @return void
	 */
    public void deploymentProcessDefinition(){
        Deployment deployment = repositoryService//与流程定义和部署对象相关的Service  
                        .createDeployment()//创建一个部署对象  
                        .name("tradeProcess")//添加部署的名称  
                        .addClasspathResource("processes/tradeProcess.bpmn")//从classpath的资源中加载，一次只能加载一个文件  
                        .addClasspathResource("processes/tradeProcess.png")//从classpath的资源中加载，一次只能加载一个文件  
                        .deploy();//完成部署  
        logger.debug("部署ID："+deployment.getId());
        logger.debug("部署名称："+deployment.getName());
    } 
    
	/**
	 * 启动流程
	 * @Title：
	 * @Description: 
	 * @param submitter 提交人
	 * @return void
	 */
	public void startProcess(String submitter) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("submitter", submitter);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("tradeProcess", map);
		logger.debug("流程实例ID:" + pi.getId());
		logger.debug("流程定义ID:" + pi.getProcessDefinitionId());
	}
	
	/**
	 * 根据审批人id查询需要审批的任务
	 * @param userId
	 * @return
	 */
	public List<Task> findTaskByUserId(String userId) {
		List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();
		return list;
	}
	
	/**
	 * 审批完成
	 * @param taskId 审批的任务id
	 * @param userId 审批人的id
	 * @param audit  审批意见：通过（pass）or驳回（reject）
	 */
	 public void completeTaskByUser(String taskId, String userId, String audit) {
		 Map<String, Object> map = new HashMap<>();
		 //1、认领任务
		 taskService.claim(taskId, userId);
		//2.完成任务
		 map.put("audit",audit);
		 taskService.complete(taskId, map);
		 logger.debug("完成任务，任务ID" + taskId);
	 }
	 
	 /**
	  * 结束流程实例
	  * @Title：
	  * @Description: 
	  * @param processInstanceId 流程id
	  * @param deleteReason 删除原因
	  * @return void
	  */
	 public void deleteProcessInstance(String processInstanceId, String deleteReason){
		 runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
		 logger.debug("删除成功!");
	 }
	
	/**
	 * 根据流程id查看该流程是否结束
	 * @param processInstanceId
	 * @return
	 */
	public boolean queryProcessIsEnd(String processInstanceId){
		HistoricProcessInstance result = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(result != null && result.getStartTime() != null && result.getEndTime() != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 提交审批
	 * @Title：
	 * @Description: 
	 * @param taskId
	 * @return void
	 */
	public void commitApproval(String taskId){
		taskService.complete(taskId);
		logger.debug("提交审批成功----->" + taskId);
	}
	
	/**
	 * 获取流程图
	 * @param processDefId
	 * @return
	 */
	public static InputStream findProcessPic(String processDefId) {
		ProcessDefinition result = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefId).singleResult();
		String name = result.getDiagramResourceName();
		InputStream inputStream = repositoryService.getResourceAsStream(result.getDeploymentId(), name);
		return inputStream;
	}
}