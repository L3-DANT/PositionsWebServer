package com.l3dant.dao;

import org.bson.Document;

import com.l3dant.bean.Utilisateur;
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
		                        .append("localisation", new Document()
		                        		.append("latitude", u.getLocalisation().getLatitude())
		                        		.append("longitude", u.getLocalisation().getLongitude())
		                        		.append("date", u.getLocalisation().getDate())
		                        		.append("heure", u.getLocalisation().getHeure())));
		
		return true;
	}
	
}
