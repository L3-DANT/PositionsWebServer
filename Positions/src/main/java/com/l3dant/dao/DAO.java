package com.l3dant.dao;

import com.mongodb.client.MongoDatabase;

public abstract class DAO<T> {
	
	MongoDatabase mongoDatabase = ConnexionMongo.getDatabase();
	

	public abstract T find(T t);
	
	public abstract boolean create(T t);
	
	public abstract boolean update(T t);
	
	public abstract boolean delete(T t);
	/*
	public MongoDatabase getMongoDatabase() {
		return mongoDatabase;
	}

	public void setMongoDatabase(MongoDatabase mongoDatabase) {
		this.mongoDatabase = mongoDatabase;
	}
	*/
}