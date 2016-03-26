package com.l3dant.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
		return ut.equals(u);
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
