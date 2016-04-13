package com.l3dant.dao;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.l3dant.bean.Localisation;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class LocalisationDAO implements DAO<Localisation>{

private final MongoCollection<Document> collUtilisateurs;
	
	public LocalisationDAO() {
		collUtilisateurs = ConnexionMongo.getDatabase().getCollection("utilisateurs");
	}
	
	@Override
	public Localisation find(String pseudo) {
		FindIterable<Document> result = collUtilisateurs.find(eq("pseudo", pseudo));
		Document document = result.first();
		if(document == null){
			return null;
		}
		
		Localisation l = new Localisation();
		l.setLongitude(document.getDouble("longitude"));
		l.setLatitude(document.getDouble("latitude"));
		l.setDate(document.getString("date"));
		l.setHeure(document.getString("heure"));
		
		return l;
	}

	@Override
	public Localisation create(Localisation t) {
		return null;
	}

	@Override
	public Localisation update(Localisation t) {
		return null;
	}
	
	public Localisation update(Localisation loc, String pseudo) {
		collUtilisateurs.findOneAndUpdate(
				eq("pseudo", pseudo),
				new Document("$set", new Document("localisation", 
									 new Document("latitude", loc.getLatitude())
										.append("longitude", loc.getLongitude())
										.append("date", loc.getDate())
										.append("heure", loc.getHeure())
										)
				)
        );
		
		
		return loc;
	}

	@Override
	public boolean delete(Localisation t) {
		return false;
	}
	
}
