package com.pengkongtec.service.impl;

import com.pengkongtec.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务
 * @ClassName: SchedulingConfig.java 
 * @Description: SchedulingConfig.java
 * @author: xw
 * @date: 2018年4月18日下午8:35:54
 */
@Component
public class SchedulingConfig {
    private final Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

    @Autowired
    private UserService userService;

//    @Scheduled(cron = "0/10 * * * * ?") // 每10秒执行一次
    public void scheduler(){
        logger.debug( "定时任务开始");
//        List<User> userList = userService.getList(99);
//        logger.debug(userList.toString());
    }

}
