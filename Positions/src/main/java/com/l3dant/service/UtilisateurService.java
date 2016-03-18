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
	
	@POST
	@Path("/inscription")
	@Produces("application/json")
	@Consumes("application/json")
	public Utilisateur inscription(Utilisateur u){
		System.out.println("inscription");
		UtilisateurDAO uDAO = new UtilisateurDAO(connectionBase());
		
		uDAO.inscrire(u);
		//m.close();
		return u;
	}
	
	@GET
	@Path("/connexion")
	@Produces("application/json")
	@Consumes("application/json")
	public String connexion(Utilisateur u){
		System.out.println("connexion");
		UtilisateurDAO uDAO = new UtilisateurDAO(connectionBase());
		
		String message;
		if(uDAO.connexion(u)){
			message = "inscription";
		} else {
			message = "L'identifiant ou le mot de passe sont incorrects.";
		}
		
		m.close();
		
		return message;
	}
	
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String test(){
		System.out.println("test");
		return "coucou";
	}
	
	
	public MongoDatabase connectionBase(){
		m = new MongoClient("127.0.0.1", 27017); // changer les valeurs
		MongoDatabase db = m.getDatabase("Positions");
		return db;
	}
	
	
	
}
