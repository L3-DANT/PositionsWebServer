package com.l3dant.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class ConnexionMongo {
	private static final String uri = "mongodb://positions:positions75@ds013212.mlab.com:13212/positions";//"127.0.0.1";//
	
	//Singleton
	public static MongoDatabase getDatabase(){
		return Init.mongoDatabase;
	}

	// Real singleton implementation. As this class is static, it won't be loaded when "ConnexionMongo" will be.
	// So this DB connexion will be made when someone will call getDatabase.
	// Some doc here about the Holder : http://thecodersbreakfast.net/index.php?post/2008/02/25/26-de-la-bonne-implementation-du-singleton-en-java
	private static class Init {
		private static final MongoDatabase mongoDatabase = new MongoClient(new MongoClientURI(uri)).getDatabase("positions");
		

	}
	
}
