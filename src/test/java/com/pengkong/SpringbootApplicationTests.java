package com.pengkong;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.SpringbootApplication;
import com.alibaba.fastjson.JSONObject;
import com.pengkongtec.dao.LoginDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class SpringbootApplicationTests {
	@Resource
	private LoginDao loginDao;

	@Test
	public void contextLoads() {
		JSONObject user = loginDao.getUser("admin", "123456");
		System.out.println("------------>" + user);
	}

}
