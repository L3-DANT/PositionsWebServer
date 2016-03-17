package com.l3dant.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.l3dant.bean.*;
import com.l3dant.dao.UtilisateurDAO;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@Path("/utilisateur")
public class UtilisateurService {
	
	private MongoClient m;
	
	@GET
	@Produces("application/json")
	public String inscription(Utilisateur u){
		System.out.println("inscription");
		UtilisateurDAO uDAO = new UtilisateurDAO(connectionBase());
		
		/*Utilisateur u = new Utilisateur();
		u.setNom("Duchenne");
		u.setPrenom("Sébastien");
		u.setPseudo("seb");
		u.setMotDePasse("sebastien");
		
		Localisation loc = new Localisation();
		loc.setLatitude(2.0);
		loc.setLongitude(49.0);
		loc.setDate("17/03/2016");
		loc.setHeure("21:58:26");
		
		u.setLocalisation(loc);*/
		
		uDAO.inscrire(u);
		m.close();
		return "inscription";
	}
	
	
	public MongoDatabase connectionBase(){
		m = new MongoClient("127.0.0.1", 27017); // changer les valeurs
		MongoDatabase db = m.getDatabase("Positions");
		return db;
	}
	
	
	
}
