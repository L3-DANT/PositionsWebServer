package com.l3dant.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.l3dant.bean.Contact;
import com.l3dant.bean.Localisation;
import com.l3dant.bean.Utilisateur;
import com.l3dant.dao.DAO;
import com.l3dant.dao.DAOFactory;
import com.l3dant.dao.LocalisationDAO;
import com.pusher.rest.Pusher;
import com.pusher.rest.data.Result;

@Path("/localisation")
@Produces("application/json")
@Consumes("application/json")
public class LocalisationService {
	
	private static DAO<Localisation> lDAO = DAOFactory.getLocalisationDAO();
	private static DAO<Utilisateur> uDAO = DAOFactory.getUtilisateurDAO();
	
	@POST
	@Path("/updateLoc")
	public boolean updateLocalisation(Contact c){
		System.out.println("updateLoc - pseudo:" + c.getPseudo());
		
		Contact contact = new Contact(c.getPseudo(), c.getLoc());
		
		//paramètres pusher
		String APP_KEY = "f145cb57089f977c5857";
		String APP_SECRET = "4860adff6e6808ba957b";
		String APP_ID = "206945";
		
		Pusher pusher = new Pusher(APP_ID, APP_KEY, APP_SECRET);
		pusher.setCluster("eu");
		pusher.setEncrypted(false);
		
		Utilisateur u = uDAO.find(c.getPseudo());
		Result r = null;
		if(u.isShareLocation()){
			((LocalisationDAO)lDAO).update(c.getLoc(), c.getPseudo());
			r = pusher.trigger(contact.getPseudo(), "update", contact);
		}
		
		System.out.println(r.getMessage());
		return true;
	}
	
	
	
	
	
}
