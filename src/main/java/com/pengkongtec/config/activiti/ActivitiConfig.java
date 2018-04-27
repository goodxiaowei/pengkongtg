package com.pengkongtec.config.activiti;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 加载activiti配置文件
 * @ClassName: ActivitiConfig.java 
 * @Description: ActivitiConfig.java
 * @author: xw
 * @date: 2018年4月18日下午8:32:12
 */
@Configuration
@ImportResource(locations={"classpath:activiti.cfg.xml"})
public class ActivitiConfig {
}
