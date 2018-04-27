package com.pengkongtec.controller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pengkongtec.service.BpmService;
import com.pengkongtec.service.TradeInfoService;

/**
 *
 * 测试Controller
 */

@Controller
//@RequestMapping(value = "/api/user")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TradeInfoService tradeService;
    @Autowired
    private BpmService bpmService;
    @Autowired
	private ManagementService managementService;

	/**
	 * 生成交易信息审批流程
	 * @param
	 * @return
	 */
	@RequestMapping("/tradeProcess")
    public void tradeProcess() {

        //根据bpmn文件部署流程
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/tradeProcess.bpmn").deploy();
        //获取流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        //启动流程定义，返回流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
        String processId = pi.getId();
        System.out.println("流程创建成功，当前流程实例ID：" + processId);

        Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("第一次执行前，任务名称：" + task.getName());
        taskService.complete(task.getId());
        task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("第二次执行前，任务名称：" + task.getName());
        taskService.complete(task.getId());

        task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("task为null，任务执行完毕：" + task);
    }
    
	
	/**
	 * 查询当前用户的任务列表
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTaskByUserId")
	public Object getTaskByUserId(String userId,HttpServletRequest request) {
		logger.debug(userId);
//		return tradeService.getByUserId(userId);
		return null;
	}
	
	/**
	 * 处理完成任务
	 * @param taskId
	 * @param userId
	 * @param audit
	 * @param request
	 * @return
	 */
	@RequestMapping("/completeTask")
	public String completeTask(String taskId,String userId,String audit,HttpServletRequest request) {
		tradeService.completeTaskByUser(taskId, userId, audit);
		return "审批完成...";
	}
	
	
	@RequestMapping("/showImg")
	public void showImg(String processDefId,HttpServletRequest request,HttpServletResponse response) {
		try {
			InputStream inputStream = bpmService.findProcessPic(processDefId);
			byte[] b = new byte[1024];
			int len = -1;
			while((len = inputStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		} catch (IOException e) {
			logger.error("读取流程图片出错:{" + e + "}");
		}
		
	}

	@RequestMapping("/index")
	public String index(){
		return "index";
	}
}
