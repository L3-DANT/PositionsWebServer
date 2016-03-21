package com.l3dant.dao;

import com.l3dant.bean.Utilisateur;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DAOFactory {
	
	private static final MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
	private static final MongoDatabase mongoDatabase = mongoClient.getDatabase("Positions");
	
	public static DAO<Utilisateur> getUtilisateurDAO(){
		return new UtilisateurDAO(mongoDatabase);
	}
	
	//mettre ici les autres servicesDAO
	
	
}
