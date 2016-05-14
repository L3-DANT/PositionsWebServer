package com.l3dant.service;

import java.nio.channels.Channel;
import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	
	@POST
	@Path("/updateLoc")
	public boolean updateLocalisation(Utilisateur u){
		((LocalisationDAO)lDAO).update(u.getLocalisation(), u.getPseudo());
		
		Contact contact = new Contact(u.getPseudo(), u.getLocalisation());
		
		//paramètres pusher
		String APP_KEY = "f145cb57089f977c5857";
		String APP_SECRET = "4860adff6e6808ba957b";
		String APP_ID = "206945";
		
		Pusher pusher = new Pusher(APP_ID, APP_KEY, APP_SECRET);
		pusher.setCluster("eu");
		pusher.setEncrypted(false);
		Result r = pusher.trigger(contact.getPseudo(), "update", contact);
		
		System.out.println(r.getMessage());
		return true;
	}
	
	
	
	
	
}
