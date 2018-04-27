package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@EnableAutoConfiguration
@MapperScan(basePackages = "com.pengkongtec.dao")
//@EnableScheduling
@ImportResource(locations={"classpath:kaptcha.xml"})
public class SpringbootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SpringbootApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(SpringbootApplication.class);
	}
}
