package com.l3dant.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.l3dant.bean.Contact;
import com.l3dant.bean.Localisation;
import com.l3dant.dao.DAO;
import com.l3dant.dao.DAOFactory;



@Path("/contacts")
@Produces("application/json")
@Consumes("application/json")
public class ContactService {
	
	private static DAO<Contact> cDAO = DAOFactory.getContactDAO();
	
	@POST
	@Path("/adding")
	public void addAmi(Contact c){ //Invitation invit
		cDAO.update(c);
	}
	
}
