package com.pengkongtec.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pengkongtec.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Resource
	private ProductService productService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String findList(@RequestBody JSONObject requestParam){
		return JSON.toJSONString(productService.findList(requestParam));
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(@RequestBody JSONObject requestParam){
		return JSON.toJSONString(productService.addProduct(requestParam));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteProduct(@RequestBody JSONObject requestParam){
		return JSON.toJSONString(productService.deleteProduct(requestParam));
	}
}
