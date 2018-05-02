package com.pengkongtec.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 日志实体类
 * @ClassName: LoggerEntity.java 
 * @Description: LoggerEntity.java
 * @author: xw
 * @date: 2018年5月2日下午4:30:37
 */
public class LoggerEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5182286549154289744L;
	private Long id;
	private String ip;//请求ip
	private String uri;//请求地址
	private String type;//请求方式 
	private String declaringClass;//全类名
	private String method;//请求方法
	private String returnType;//返回值类型
	private String paramData;//请求参数
	private Timestamp requestTime;//请求时间
	private Timestamp responseTime;//响应时间
	private String exceptionType;//异常类型
	private String exceptionMsg;//异常消息
	
	public LoggerEntity() {
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeclaringClass() {
		return declaringClass;
	}

	public void setDeclaringClass(String declaringClass) {
		this.declaringClass = declaringClass;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData;
	}

	public Timestamp getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}

	public Timestamp getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Timestamp responseTime) {
		this.responseTime = responseTime;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	@Override
	public String toString() {
		return "LoggerEntity [id=" + id + ", ip=" + ip + ", uri=" + uri
				+ ", type=" + type + ", declaringClass=" + declaringClass
				+ ", method=" + method + ", returnType=" + returnType
				+ ", paramData=" + paramData + ", requestTime=" + requestTime
				+ ", responseTime=" + responseTime + ", exceptionType="
				+ exceptionType + ", exceptionMsg=" + exceptionMsg + "]";
	}
}
