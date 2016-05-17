package com.l3dant.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.l3dant.bean.Contact;
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
		if(uDAO.find(demandeur) == null || uDAO.find(concerne) == null || ((ContactDAO)cDAO).find(demandeur,concerne))
			return false;
		
		((ContactDAO)cDAO).create(demandeur, concerne);

		return true;
	}
	
}
