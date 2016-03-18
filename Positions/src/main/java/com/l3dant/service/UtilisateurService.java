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
		UtilisateurDAO uDAO = new UtilisateurDAO();
		
		uDAO.inscrire(u);
		//m.close();
		return u;
	}
	
	@POST
	@Path("/connexion")
	@Produces("plain/text")
	@Consumes("application/json")
	public String connexion(Utilisateur u){
		System.out.println("connexion");
		UtilisateurDAO uDAO = new UtilisateurDAO();
		
		String message;
		if(uDAO.connexion(u)){
			message = "Vous êtes connecté.";
		} else {
			message = "L'identifiant ou le mot de passe sont incorrects.";
		}
		
		
		return message;
	}
	
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String test(){
		System.out.println("test");
		return "coucou";
	}
	
	
	
	
}
