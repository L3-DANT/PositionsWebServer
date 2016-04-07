package com.l3dant.dao;


import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import com.l3dant.bean.Localisation;
import com.l3dant.bean.Utilisateur;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;


import static com.mongodb.client.model.Filters.*;

public class UtilisateurDAO implements DAO<Utilisateur>{

	private final MongoCollection<Document> collUtilisateurs;
	
	public UtilisateurDAO() {
		collUtilisateurs = ConnexionMongo.getDatabase().getCollection("utilisateurs");
	}
	
	@Override
	public Utilisateur find(String name) {
		FindIterable<Document> result = collUtilisateurs.find(eq("pseudo", name));
		Document document = result.first();
		if(document == null){
			return null;
		}

		Utilisateur ut = new Utilisateur();
		ut.setNom(document.getString("nom"));
		ut.setPrenom(document.getString("prenom"));
		ut.setPseudo(document.getString("pseudo"));
		ut.setMotDePasse(document.getString("motDePasse"));
		
		Document doc = (Document) document.get("localisation");

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
	public Utilisateur create(Utilisateur u) {
		collUtilisateurs.insertOne(
				new Document()
                .append("nom", u.getNom())
                .append("prenom", u.getPrenom())
                .append("pseudo", u.getPseudo())
                .append("motDePasse", u.getMotDePasse())
                .append("token", u.getToken())
                .append("localisation", null));
		return u;
	}

	@Override
	public Utilisateur update(Utilisateur u) {
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
		
		return u;
	}

	@Override
	public boolean delete(Utilisateur u) {
		collUtilisateurs.deleteOne(eq("pseudo", u.getPseudo()));
		return true;
	}
	
}
