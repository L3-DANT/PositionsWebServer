package com.l3dant.dao;

public interface DAO<T> {

	T find(String name);
	
	T create(T t);
	
	T update(T t);
	
	boolean delete(T t);

}
