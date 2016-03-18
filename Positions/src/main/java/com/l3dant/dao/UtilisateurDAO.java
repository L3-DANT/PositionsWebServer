package com.l3dant.dao;

import org.bson.Document;

import com.l3dant.bean.Utilisateur;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class UtilisateurDAO extends DAO<Utilisateur>{

	public UtilisateurDAO(MongoDatabase mongodb) {
		super(mongodb);
	}

	public boolean addUtilisateur(Utilisateur utilisateur){
		
		return true;
	}

	@Override
	public Utilisateur find() {

		return null;
	}

	@Override
	public boolean create() {

		return false;
	}

	@Override
	public boolean update() {

		return false;
	}

	@Override
	public boolean delete() {

		return false;
	}
	
	public boolean inscrire(Utilisateur u){
		getMongodb().getCollection("utilisateurs").insertOne(
		                new Document()
		                        .append("nom", u.getNom())
		                        .append("prenom", u.getPrenom())
		                        .append("pseudo", u.getPseudo())
		                        .append("motDePasse", u.getMotDePasse())
		                        .append("localisation", null));
		
		return true;
	}
	
	public boolean connexion(Utilisateur u){
		/*boolean connexion = false;
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("nom", u.getPseudo());
		DBCursor cursor = collection.find(whereQuery);
		while(cursor.hasNext()) {
			String mdp = cursor.next();
		    System.out.println(mdp);
		    if(u.getMotDePasse().equals(mdp)){
		    	connexion = true;
		    }
		}
		*/
		return true;//connexion;
	}
	
}
