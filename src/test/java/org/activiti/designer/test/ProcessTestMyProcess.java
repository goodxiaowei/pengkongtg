package org.activiti.designer.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestMyProcess {

//	private String filename = "H:\WorkSpace\pengkongtg\src\main\resources\processes\MyProcess.bpmn";
//
//	@Rule
//	public ActivitiRule activitiRule = new ActivitiRule();
//
//	@Test
//	public void startProcess() throws Exception {
//		RepositoryService repositoryService = activitiRule.getRepositoryService();
//		repositoryService.createDeployment().addInputStream("myProcess.bpmn20.xml",
//				new FileInputStream(filename)).deploy();
//		RuntimeService runtimeService = activitiRule.getRuntimeService();
//		Map<String, Object> variableMap = new HashMap<String, Object>();
//		variableMap.put("name", "Activiti");
//		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", variableMap);
//		assertNotNull(processInstance.getId());
//		System.out.println("id " + processInstance.getId() + " "
//				+ processInstance.getProcessDefinitionId());
//	}
}