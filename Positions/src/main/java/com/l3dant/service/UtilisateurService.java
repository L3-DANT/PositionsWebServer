package com.l3dant.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.RandomStringUtils;

import com.l3dant.bean.*;
import com.l3dant.dao.DAO;
import com.l3dant.dao.DAOFactory;

@Path("/utilisateur")
@Produces("application/json")
@Consumes("application/json")
public class UtilisateurService {
	
	private static DAO<Utilisateur> uDAO = DAOFactory.getUtilisateurDAO();
	
	@POST
	@Path("/inscription")
	public boolean inscription(Utilisateur u){
		System.out.println("inscription");
		
		if(uDAO.find(u.getPseudo()) == null){
			u.setToken(RandomStringUtils.random(32, true, true));
			uDAO.create(u);
			return true;
		}
		return false;
	}
	
	@POST
	@Path("/connexion")
	public boolean connexion(Utilisateur u){
		System.out.println("connexion");
		
		Utilisateur ut = uDAO.find(u.getPseudo());

		if (ut == null) {
			return false;
		}
		return true;
	}
	
	@DELETE
	@Path("/suppression")
	@Consumes("application/json")
	@Produces("application/json")
	public boolean supprimerCompte(Utilisateur u){
		System.out.println("supprimer");
		return uDAO.delete(u);
	}
	
	
	@POST
	@Path("/test")
	public boolean test(Utilisateur u){
		System.out.println("test"+u.getNom());
		u.setNom("bcb");
		u.setPrenom("iuyt");
		System.out.println("test"+u.getNom());
		uDAO.update(u);
		return true;
	}
	
}
