package com.l3dant.dao;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.l3dant.bean.Utilisateur;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

public class UtilisateurDAO extends DAO<Utilisateur>{

	public UtilisateurDAO() {}

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
		
		getMongoDatabase().getCollection("utilisateurs").insertOne(
		                new Document()
		                        .append("nom", u.getNom())
		                        .append("prenom", u.getPrenom())
		                        .append("pseudo", u.getPseudo())
		                        .append("motDePasse", u.getMotDePasse())
		                        .append("localisation", null));
		
		return true;
	}
	
	public boolean connexion(Utilisateur u){
		boolean connexion = false;
		
		MongoClient m = new MongoClient("127.0.0.1", 27017); // changer les valeurs
		MongoDatabase db = m.getDatabase("Positions");

		MongoCollection<Document> collection = db.getCollection("utilisateurs");
		
		FindIterable<Document> cursor = collection.find(and(eq("pseudo", u.getPseudo()), eq("motDePasse", u.getMotDePasse())));
		
		if(cursor.first() == null){
		} else {
			connexion = true;
		}
		
		return connexion;
	}
	
}
