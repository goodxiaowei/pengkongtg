package com.pengkongtec.dao;

import java.util.List;

public interface BaseDao<T> {
	
	Integer getCount(T t);

	Integer insert(T t);
	
	Integer delete(T t);
	
	Integer update(T t);

	List<T> getList(T t);
	
	T getListById(T t);
}
