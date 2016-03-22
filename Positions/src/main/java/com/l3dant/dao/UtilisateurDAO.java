package com.l3dant.dao;

import org.bson.BSON;
import org.bson.Document;

import com.l3dant.bean.Localisation;
import com.l3dant.bean.Utilisateur;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

public class UtilisateurDAO extends DAO<Utilisateur>{

	private MongoCollection<Document> collUtilisateurs;
	
	public UtilisateurDAO(MongoDatabase mongoDatabase) {
		super(mongoDatabase);
		collUtilisateurs = getMongoDatabase().getCollection("utilisateurs");
	}
	
	@Override
	public Utilisateur find(Utilisateur u) {
		FindIterable<Document> result = collUtilisateurs.find(eq("pseudo", u.getPseudo()));
		Utilisateur ut = null;
		if(result.first() == null){
			return null;
		}
		
		ut = new Utilisateur();
		ut.setNom(result.first().getString("nom"));
		ut.setPrenom(result.first().getString("prenom"));
		ut.setPseudo(result.first().getString("pseudo"));
		ut.setMotDePasse(result.first().getString("motDePasse"));
		
		Document doc = (Document)result.first().get("localisation");

		if(doc != null){
			Localisation loc = new Localisation();
			loc.setLatitude(doc.getDouble("latitude"));
			loc.setLatitude(doc.getDouble("longitude"));
			loc.setDate(doc.getString("date"));
			loc.setHeure(doc.getString("heure"));
			ut.setLocalisation(loc);
		}
		return ut;
	}
	
	@Override
	public boolean create(Utilisateur u) {
		collUtilisateurs.insertOne(
				new Document()
                .append("nom", u.getNom())
                .append("prenom", u.getPrenom())
                .append("pseudo", u.getPseudo())
                .append("motDePasse", u.getMotDePasse())
                .append("localisation", null));
		return true;
	}

	@Override
	public boolean update(Utilisateur u) {
		collUtilisateurs.findOneAndUpdate(
				eq("pseudo", u.getPseudo()),
				new Document("$set", new Document("nom", u.getNom()))
					.append("$set", new Document("prenom", u.getPrenom()))
					.append("$set", new Document("motDePasse", u.getMotDePasse()))
					.append("$set", new Document("localisation", 
										new Document("latitude", u.getLocalisation().getLatitude())
										.append("longitude", u.getLocalisation().getLongitude())
										.append("date", u.getLocalisation().getDate())
										.append("heure", u.getLocalisation().getHeure())
										)
					)
                );
		
		return false;
	}

	@Override
	public boolean delete(Utilisateur u) {

		return false;
	}
	
}
