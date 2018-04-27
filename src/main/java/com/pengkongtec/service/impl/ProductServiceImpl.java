package com.pengkongtec.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.pengkongtec.dao.ProductDao;
import com.pengkongtec.service.ProductService;
import com.pengkongtec.utils.CommonUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductDao productDao;
	
	@Override
	public JSONObject addProduct(JSONObject requestParam) {
		Integer result = productDao.insert(requestParam);
		if(result > 0){
			return CommonUtil.successJson();
		}else{
			return CommonUtil.failJson();
		}
	}

	@Override
	public JSONObject deleteProduct(JSONObject requestParam) {
		Integer result = productDao.delete(requestParam);
		if(result > 0){
			return CommonUtil.successJson();
		}else{
			return CommonUtil.failJson();
		}
	}

	@Override
	public JSONObject updateProduct(JSONObject requestParam) {
		Integer result = productDao.update(requestParam);
		if(result > 0){
			return CommonUtil.successJson();
		}else{
			return CommonUtil.failJson();
		}
	}

	@Override
	public JSONObject findList(JSONObject requestParam) {
		return CommonUtil.successPage(requestParam, productDao.getList(requestParam), productDao.getCount(requestParam));
	}

}
