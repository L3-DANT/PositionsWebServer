package com.l3dant.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConnexionMongo {
	private static String url = "127.0.0.1";
	private static MongoClient mongoClient;
	private static MongoDatabase mongoDatabase;
	
	//Singleton
	public static MongoDatabase getDatabase(){
		if(mongoDatabase == null){
			mongoClient = new MongoClient(url);
			mongoDatabase = mongoClient.getDatabase("Positions");
		}
		return mongoDatabase;
	}
	
}
