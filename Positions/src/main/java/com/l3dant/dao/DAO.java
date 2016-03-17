package com.l3dant.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public abstract class DAO<T> {
	private MongoDatabase mongodb;
	
	public DAO(MongoDatabase mongodb){
		this.mongodb = mongodb;
	}
	
	public abstract T find();
	
	public abstract boolean create();
	
	public abstract boolean update();
	
	public abstract boolean delete();
	

	public MongoDatabase getMongodb() {
		return mongodb;
	}
	
}
