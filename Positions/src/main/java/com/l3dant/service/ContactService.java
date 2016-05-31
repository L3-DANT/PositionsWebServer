package com.l3dant.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.l3dant.bean.Contact;
import com.l3dant.bean.Invitation;
import com.l3dant.bean.Utilisateur;
import com.l3dant.dao.ContactDAO;
import com.l3dant.dao.DAO;
import com.l3dant.dao.DAOFactory;



@Path("/contacts")
@Produces("application/json")
@Consumes("application/json")
public class ContactService {
	
	private static DAO<Contact> cDAO = DAOFactory.getContactDAO();
	private static DAO<Utilisateur> uDAO = DAOFactory.getUtilisateurDAO();
	
	public static boolean addAmi(String demandeur, String concerne){ //Invitation invit
		System.out.println("addAmi");
		if(uDAO.find(demandeur) == null || uDAO.find(concerne) == null || ((ContactDAO)cDAO).find(demandeur,concerne))
			return false;
		
		((ContactDAO)cDAO).create(demandeur, concerne);

		return true;
	}
	
	//suppression d'un string
	@Path("/supprAmi")
	@POST
	public boolean supprAmi(@QueryParam("initiateur") String initiateur, @QueryParam("exAmi") String exAmi){
		((ContactDAO)cDAO).delete(initiateur, exAmi);
		return true;
	}
	
}
