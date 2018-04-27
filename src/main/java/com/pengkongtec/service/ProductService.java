package com.pengkongtec.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 产品
 * @ClassName: ProductService.java 
 * @Description: ProductService.java
 * @author: xw
 * @date: 2018年4月27日上午10:02:03
 */
public interface ProductService {

	public JSONObject addProduct(JSONObject requestParam);
	
	public JSONObject deleteProduct(JSONObject requestParam);
	
	public JSONObject updateProduct(JSONObject requestParam);
	
	public JSONObject findList(JSONObject requestParam);
}
