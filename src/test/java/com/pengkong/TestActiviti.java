package com.pengkong;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.SpringbootApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class TestActiviti {
	
	@Resource
	private RepositoryService repositoryService;
	
	@Resource
	private TaskService taskService;
	
	@Resource
	private RuntimeService runtimeService;
	
	@Test
	public void test() {
//		repositoryService.
	}
	
	@Resource
	private HistoryService historyService;

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
//	ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");  
    // 获取流程引擎对象  
//    ProcessEngine processEngine = pec.buildProcessEngine();  
	
	@Test
	public void test1(){
		System.out.println("---------->" + processEngine.toString());
	}
    /**部署流程定义*/  
    @Test  
    public void deploymentProcessDefinition(){
        Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service  
                        .createDeployment()//创建一个部署对象  
                        .name("tradeProcess")//添加部署的名称  
                        .addClasspathResource("processes/tradeProcess.bpmn")//从classpath的资源中加载，一次只能加载一个文件  
                        .addClasspathResource("processes/tradeProcess.png")//从classpath的资源中加载，一次只能加载一个文件  
                        .deploy();//完成部署  
        System.out.println("部署ID："+deployment.getId());//70001 
        System.out.println("部署名称："+deployment.getName());//tradeProcess
    }  
      
    /**启动流程实例*/  
    @Test  
    public void startProcessInstance(){  
        //流程定义的key  
        String processDefinitionKey = "tradeProcess";
        //启动流程的同时，指定提交人
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("submitter", "admin2");
//        ProcessInstance pi = processEngine.getRuntimeService()//与正在执行的流程实例和执行对象相关的Service  
//                        .startProcessInstanceByKey(processDefinitionKey, variables);//使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动  
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        System.out.println("流程实例ID:"+pi.getId());//流程实例ID    72501
        System.out.println("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID   tradeProcess:8:70004
    }  
      
      
    /**查询当前人的个人任务*/  
    @Test  
    public void findMyPersonalTask(){  
        String assignee = "admin2";  
        List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).list();
        
//        List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service  
//                        .createTaskQuery()//创建任务查询对象  
//                        .taskAssignee(assignee)//指定个人任务查询，指定办理人  
//                        .list();  
        if(list!=null && list.size()>0){  
            for(Task task:list){  
                System.out.println("任务ID:"+task.getId());  //72505
                System.out.println("任务名称:"+task.getName());  
                System.out.println("任务的创建时间:"+task.getCreateTime());  
                System.out.println("任务的办理人:"+task.getAssignee());  
                System.out.println("流程实例ID："+task.getProcessInstanceId());  
                System.out.println("执行对象ID:"+task.getExecutionId());  
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());  
                System.out.println("########################################################");  
            }  
        }  
    }  
    
    /**查询当前人的个人任务*/  
    @Test  
    public void findMyProcessedByMeTask(){  
//        String assignee = "李四";
//    	String[] candidateUsers = {"a","b","c"};
//    	Map<String, Object> map = new HashMap<String, Object>();
//    	map.put("审核员", Arrays.asList(candidateUsers));
//    	List<Task> list = taskService.createTaskQuery().taskCandidateGroup("group1").list();
    	List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned("tradeProcesser").list();
//        List<Task> list = taskService.createTaskQuery().taskAssignee("b").list();
        if(list!=null && list.size()>0){  
            for(Task task:list){  
                System.out.println("任务ID:"+task.getId());  
                System.out.println("任务ID:"+task.getDelegationState().toString());  
                System.out.println("任务名称:"+task.getName());  
                System.out.println("任务的创建时间:"+task.getCreateTime());  
                System.out.println("任务的办理人:"+task.getAssignee());  
                System.out.println("流程实例ID："+task.getProcessInstanceId());  
                System.out.println("执行对象ID:"+task.getExecutionId());  
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());  
                System.out.println("########################################################");  
            }  
        }  
    }  
    
    /**审批通过*/  
    @Test  
    public void completeMyProcessedByMeTask(){  
        //任务ID  
        String taskId = "75002";
        taskService.complete(taskId);
        System.out.println("完成任务：任务ID："+taskId);
    }  
      
    /**完成我的任务*/  
    @Test  
    public void completeMyPersonalTask(){  
        //任务ID  
        String taskId = "72505";  
        processEngine.getTaskService()//与正在执行的任务管理相关的Service  
                    .complete(taskId);
        System.out.println("完成任务：任务ID："+taskId);
    }  
    
  /**创建组对象**/
    @Test
    public void run1(){
    	Group group = new GroupEntity();
    	group.setId("group1");
    	group.setName("审核部");
    	//将组保存在数据库中 act_id_group
    	processEngine.getIdentityService().saveGroup(group);
    }
    /**
     * 创建用户01
     * @Title：
     * @Description: 
     * @param TestActiviti.java
     * @return void
     */
    @Test
    public void run2() {
		User user1 = new UserEntity();
		user1.setId("01");
		user1.setFirstName("jio");
		processEngine.getIdentityService().saveUser(user1);
	}
    
    /**
     * 将用户01加入到group1组中
     * @Title：
     * @Description: 
     * @param TestActiviti.java
     * @return void
     */
    @Test
    public void run3(){
    	String userId = "01";
    	String groupId = "group1";
    	processEngine.getIdentityService().createMembership(userId, groupId);
    }
    
    @Test
    public void deleteProcessInstance(){
    	String processInstanceId = "72501";
    	runtimeService.deleteProcessInstance(processInstanceId, "我同意");
    }
    
    @Test
    public void showHistoryTask(){
    	HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
    	for (int i = 0; i < query.finished().list().size(); i++) {
    		System.out.println(query.finished().list().get(i).getId());
			System.out.println(query.finished().list().get(i).getProcessDefinitionId());

		}
    }
}