package com.l3dant.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import com.l3dant.bean.Invitation;
import com.l3dant.bean.StatutInvit;
import com.l3dant.dao.DAO;
import com.l3dant.dao.DAOFactory;

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
		
		//if(iDAO.find(demandeur, concerne) == null)
			return iDAO.create(i);
		
		//return null;
		
		//return new Gson().toJson(iDAO.update(i));
	}
	
	//A la fin de l'acceptation ou du refus on supprime l'invitation du côté de l'invité
	//On change la date à la date d'acceptation ou refus pour la notifier au demandeur
	//Dans la partie iOS on envoie que le demandeur et l'invité
	@Path("/decision")
	@POST
	public void decision(@QueryParam("b") boolean b, Invitation i){
		System.out.println(b);
		System.out.println(i.getDemandeur());
		if(b)
			i.setAccept(StatutInvit.ACCEPTEE);
		else
			i.setAccept(StatutInvit.REFUSEE);
		i.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
		iDAO.update(i);
	}
	
	
	
	//On supprime l'invit du côté de celui qui a fait la demande
	//Dans la partie iOS on envoie que le demandeur et l'invité
	@Path("/supprInvit")
	@POST
	public boolean supprInvit(Invitation i){
		iDAO.delete(i);
		return true;
	}
	
}
