package com.l3dant.service;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.l3dant.bean.*;
import com.l3dant.dao.DAO;
import com.l3dant.dao.DAOFactory;

@Path("/utilisateur")
public class UtilisateurService {
	
	private static DAO<Utilisateur> uDAO = DAOFactory.getUtilisateurDAO();
	
	@POST
	@Path("/inscription")
	@Produces("application/json")
	@Consumes("application/json")
	public boolean inscription(Utilisateur u){
		System.out.println("inscription");
		
		if(uDAO.find(u) == null){ 
			uDAO.create(u);
			return true;
		}
		return false;
	}
	
	@POST
	@Path("/connexion")
	@Produces("application/json")
	@Consumes("application/json")
	public boolean connexion(Utilisateur u){
		System.out.println("connexion");
		
		Utilisateur ut = uDAO.find(u);
		
		
		if(ut != null && u.getPseudo().equals(ut.getPseudo()) && u.getMotDePasse().equals(ut.getMotDePasse())){
			return true;
		} else {
			return false;
		}
	}
	
	@GET
	@Path("/test")
	@Produces("plain/text")
	@Consumes("application/json")
	public String test(){
		System.out.println("test");
		return "coucou";
	}
	
	@POST
	@Path("/test")
	@Produces("application/json")
	@Consumes("application/json")
	public boolean test(Utilisateur u){
		System.out.println("test"+u.getNom());
		u.setNom("bcb");
		u.setPrenom("iuyt");
		System.out.println("test"+u.getNom());
		uDAO.update(u);
		return true;
	}
	
}
