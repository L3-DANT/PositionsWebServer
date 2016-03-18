package com.l3dant.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public abstract class DAO<T> {
	private MongoClient mongo;
	private MongoDatabase db;
	
	public DAO(){
		mongo = new MongoClient("127.0.0.1", 27017); // changer les valeurs
		db = mongo.getDatabase("Positions");
	}
	
	public abstract T find();
	
	public abstract boolean create();
	
	public abstract boolean update();
	
	public abstract boolean delete();
	

	public MongoDatabase getMongoDatabase() {
		return db;
	}
	
	
	
}
