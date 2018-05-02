package com.pengkongtec.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * logger日志拦截配置
 * @ClassName: LoggerWebAppConfigurer.java 
 * @Description: LoggerWebAppConfigurer.java
 * @author: xw
 * @date: 2018年5月2日下午6:24:52
 */
@Configuration
public class LoggerWebAppConfigurer extends WebMvcConfigurerAdapter {
	
	@Bean
	public LoggerInterceptor LoggerInterceptor(){
		return new LoggerInterceptor();
	}
  
    @Override
    public void addInterceptors(InterceptorRegistry registry) {  
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(LoggerInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }  
  
}  