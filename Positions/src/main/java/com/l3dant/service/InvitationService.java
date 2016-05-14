package com.l3dant.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.bson.Document;

import com.google.gson.Gson;
import com.l3dant.bean.Invitation;
import com.l3dant.bean.Utilisateur;
import com.l3dant.dao.ConnexionMongo;
import com.l3dant.dao.DAO;
import com.l3dant.dao.DAOFactory;
import com.mongodb.client.MongoCollection;

@Path("/invitation")
@Produces("application/json")
@Consumes("application/json")
public class InvitationService {
	private static DAO<Invitation> iDAO = DAOFactory.getInvitationDAO();
	
	//Méthode pour ajouter une invitation
	//return l'id de l'invitation
	@Path("/inviteFriend")
	@POST
	public Invitation inviterAmi(@QueryParam("demandeur") String demandeur, @QueryParam("concerne") String concerne){
		System.out.println(demandeur);
		System.out.println(concerne);
		String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Invitation i = new Invitation(demandeur, concerne, date);
		
		return iDAO.create(i);
		
		//return new Gson().toJson(iDAO.update(i));
	}
	
	public void accepterInvit(Invitation i){
		
	}
	
}
