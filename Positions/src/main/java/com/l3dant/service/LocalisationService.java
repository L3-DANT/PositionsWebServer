package com.l3dant.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.l3dant.bean.Localisation;
import com.l3dant.bean.Utilisateur;
import com.l3dant.dao.DAO;
import com.l3dant.dao.DAOFactory;
import com.l3dant.dao.LocalisationDAO;

@Path("/localisation")
@Produces("application/json")
@Consumes("application/json")
public class LocalisationService {
	
	private static DAO<Localisation> lDAO = DAOFactory.getLocalisationDAO();
	
	@POST
	public Utilisateur updateLocalisation(Utilisateur u){
		((LocalisationDAO)lDAO).update(u.getLocalisation(), u.getPseudo());
		return null;
	}
	
}
