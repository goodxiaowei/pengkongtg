package com.pengkongtec.config.interceptor;

import java.sql.Timestamp;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pengkongtec.bean.LoggerEntity;
import com.pengkongtec.service.LoggerEntityService;

/**
 * 日志管理拦截器
 * @ClassName: LoggerInterceptor.java 
 * @Description: LoggerInterceptor.java
 * @author: xw
 * @date: 2018年5月2日下午6:25:11
 */
public class LoggerInterceptor implements HandlerInterceptor {
	Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	
	private String LOGGER_ENTITY = "loggerEntity";

	@Resource
	private LoggerEntityService loggerEntityService;
	
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	LoggerEntity loggerEntity = new LoggerEntity();
    	String uri = request.getRequestURI();
    	loggerEntity.setUri(uri);
    	String paramData = JSON.toJSONString(request.getParameterMap());
    	loggerEntity.setParamData(paramData);
    	String host = request.getRemoteHost();
    	loggerEntity.setIp(host);
    	loggerEntity.setType(request.getMethod());
    	loggerEntity.setRequestTime(new Timestamp(System.currentTimeMillis()));
    	request.setAttribute(LOGGER_ENTITY, loggerEntity);
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求 
    }  
  
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }
  
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {
    	JSONObject bean = JSON.parseObject(JSON.toJSONString(handler)).getJSONObject("method");
    	LoggerEntity loggerEntity = (LoggerEntity)request.getAttribute(LOGGER_ENTITY);
    	loggerEntity.setResponseTime(new Timestamp(System.currentTimeMillis()));
    	loggerEntity.setMethod(bean.getString("name"));
    	loggerEntity.setReturnType(bean.getString("returnType"));
    	loggerEntity.setDeclaringClass(bean.getString("declaringClass"));
    	if(ex != null){
    		loggerEntity.setExceptionMsg(ex.getMessage());
    		loggerEntity.setExceptionType(ex.getClass().toString());
    	}
    	JSONObject param = JSON.parseObject(JSON.toJSONString(loggerEntity));
    	param.replace("requestTime", loggerEntity.getRequestTime());
    	param.replace("responseTime", loggerEntity.getResponseTime());
    	logger.debug(param.toJSONString());
    	loggerEntityService.addLoggerEntity(param);
    	logger.debug(loggerEntity.toString());
    }  
  
}